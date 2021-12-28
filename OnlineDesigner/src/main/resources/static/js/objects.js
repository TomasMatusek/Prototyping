class DocumentObjects {
    canvases = [];

    addCanvases(canvasesConfigs) {
        for (let i = 0; i < canvasesConfigs.length; i++) {
            this.addCanvas(canvasesConfigs[i]);
        }
    }

    addCanvas(canvasConfig) {
        this._createDOMElement(canvasConfig.getId(), canvasConfig.getWidth(), canvasConfig.getHeight());
        let canvas = this._createCanvasObject(canvasConfig.getId(), canvasConfig.getBackground());
        this.canvases.push(canvas);
    }

    getCanvas(canvasId) {
        for (let i = 0; i < this.canvases.length; i++) {
            let canvas = this.canvases[i];
            if (canvas.canvasId === canvasId) {
                return canvas;
            }
        }
    }

    _createCanvasObject(id, background) {
        let canvas = new fabric.Canvas(id, {
            controlsAboveOverlay: true
        });
        canvas.set('canvasId', id);
        canvas.set('selection', false);
        canvas.set('selectionBorderColor', 'rgb(255,61,54)');

        // canvas.setOverlayImage(background, canvas.renderAll.bind(canvas), {
        //     scaleX:1,
        //     scaleY:1,
        //     top: 0,
        //     left: 0,
        //     originX: 'center',
        //     originY: 'center'
        // });

        // canvas.setBackgroundImage(background,
        //     canvas.renderAll.bind(canvas), {
        //         originX: 'left',
        //         originY: 'top'
        //     }
        // );

        return canvas;
    }

    _createDOMElement(id, width, height) {
        let canvasHtml = "<canvas id='" + id + "' width='" + width + "' height='" + height + "'></canvas>";
        // Create HTML canvas in DOM if there is no canvas yet
        if ($("#canvas-wrapper canvas:last").length === 0) {
            console.log("Creating first canvas. " + canvasHtml);
            $("#canvas-wrapper").html(canvasHtml);
        }
        // Append HTML canvas in DOM after last canvas
        else {
            console.log("Appending another canvas. " + canvasHtml);
            $("#canvas-wrapper .canvas-container:last").after(canvasHtml)
        }
    }
}

class CanvasObjects {
    rects = [];

    addRects(canvasesConfigs) {
        for (let i = 0; i < canvasesConfigs.length; i++) {
            let canvasConfig = canvasesConfigs[i];
            for (let j = 0; j < canvasConfig.getRects().length; j++) {
                let rectConfig = canvasConfig.getRects()[j];
                this.addRect(rectConfig, canvasConfig.getId());
            }
        }
    }

    addRect(rectConfig, canvasId) {
        let self = this;
        fabric.Image.fromURL(rectConfig.getBackground().getImageURL(), function (image) {
            // We will change only these
            let pattern = CanvasObjects._createPatternObject(image, rectConfig.width, rectConfig.height);

            // These are always same
            let rect = CanvasObjects._createRectObject(
                rectConfig.getTop(),
                rectConfig.getLeft(),
                rectConfig.getWidth(),
                rectConfig.getHeight(),
                rectConfig.getId(),
                canvasId,
                pattern
            );

            rect.centerObject();

            // When new image is dropped
            rect.on('drop', function(event) {
                let canvasId = event.target.canvasId;
                let rectId = event.target.rectId;
                let imageURL = event.e.dataTransfer.getData("URL");
                CanvasObjects._onImageDroppedOnRect(rectId, canvasId, imageURL);
            });

            rect.set({
                clipFor: image
            });

            canvasesRepository.getCanvas(canvasId).add(rect);
            self.rects.push(rect);
            self = undefined;
        });
    }

    getRect(rectId) {
        for (let i = 0; i < this.rects.length; i++) {
            let rect = this.rects[i];
            if (rect.rectId === rectId) {
                return rect;
            }
        }
    }

    removeRect(rectId, canvasId) {
        let tempRects = [];
        for (let i = 0; i < this.rects.length; i++) {
            let rect = this.rects[i];
            if (rect.rectId !== rectId) {
                tempRects.push(rect);
            }
        }

        canvasesRepository.getCanvas(canvasId).remove(
            rectsRepository.getRect(rectId)
        );

        this.rects = tempRects;
    }

    _getCanvasIsByRectId(rectId) {
        let parts = rectId.split(".");
        if (parts.length === 3) {
            return parts[0] + "." + parts[1];
        }
    }

    static _onImageDroppedOnRect(rectId, canvasId, imageURL) {
        let image = CanvasObjects._getImageObjectFromURL(imageURL);
        documentConfig.getRect(rectId).background = new RectBackgroundConfig(imageURL, 500, 10, 10);
        rectsRepository.removeRect(rectId, canvasId);
        rectsRepository.addRect(documentConfig.getRect(rectId), canvasId);
    }

    static _getImageObjectFromURL(imageURL) {
        let parts = imageURL.split("/");
        let imageNameParts = parts[parts.length - 1].split(".");
        let imageName = imageNameParts[0];
        return $(".uploaded-image img[src$='" + imageName + "']")[0];
    }

    static _createPatternObject(img, width, height) {
        let canvas = new fabric.StaticCanvas();

        // To fit whole image exactly in in position
        // img.set("width", 800);   // Original image width
        // img.set("height", 600);  // Original image height
        // img.set("scaleX", .459); // (800 / 367) = 2.18; 1 / 2.18 = 0.458; 367 = size of rect, height
        // img.set("scaleY", .57);  // (600 / 342) = 1.75; 1 / 1.75 = 0.57; 342 = size of rect, width


        img.set('objectCaching', false);
        img.set('strokeWidth', 0);

        if (img.height >= img.width) {
            img.scaleToHeight(height, false);
        } else {
            img.scaleToWidth(width, false);
        }

        canvas.add(img);
        canvas.renderAll();

        return new fabric.Pattern({
            source: function () {
                canvas.setDimensions({
                    width: img.getScaledWidth(),
                    height: img.getScaledHeight()
                });
                canvas.renderAll();
                return canvas.getElement();
            },
            repeat: 'no-repeat',

        });
    }

    static _createRectObject(top, left, width, height, rectId, canvasId, pattern) {
        return new fabric.Rect({
            top: top,
            left: left,
            width: width,
            height: height,
            rectId: rectId,
            canvasId: canvasId,
            stroke: '#9d9d9d',
            lockMovementX: false,
            lockMovementY: false,
            lockRotation: true,
            lockScalingX: false,
            lockScalingY: false,
            hasRotatingPoint: false,
            fill: pattern,
            strokeWidth: 0
        });
    }
}