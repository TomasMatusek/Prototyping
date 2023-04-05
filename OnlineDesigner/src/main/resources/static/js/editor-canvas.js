let configuration = {
    "id": "P1",
    "name": "Calendar 01",
    "basePrice": 9.90,
    "pages":
    [{
        "id": "P1.P1",
        "desc": "January",
        "width": 440,
        "height": 1330,
        "background": "./img/calendar_02_2020_01.jpg",
        "positions":
            [{
                "id": "P1.P1.P1",
                "top": 43,
                "left": 35,
                "width": 367,
                "height": 342
            },{
                "id": "P1.P1.P2",
                "top": 415,
                "left": 35,
                "width": 367,
                "height": 342
            }]
    },{
        "id": "P1.P2",
        "desc": "February",
        "width": 440,
        "height": 1330,
        "background": "./img/calendar_01_2020_02.jpg",
        "positions":
            [{
                "id": "P1.P1.P1",
                "top": 43,
                "left": 35,
                "width": 367,
                "height": 342
            },{
                "id": "P1.P1.P2",
                "top": 415,
                "left": 35,
                "width": 367,
                "height": 342
            }]
    }]
};

let UserSettingsRepository = {
    images: [],

    /**
     * Takes currently laded images in page and store them so they can be loaded back
     * @param images Editor.images
     */
    storeImageSettings: function (images) {
        for (let i = 0; i < images.length; i++) {
            let image = images[i];
            console.log("Storing images to UserSettingsRepository", image);

            // Remove old settings, so it can be replaced with new; one ome image allowed per position
            UserSettingsRepository.removeStoredImageSettings(image.positionId);

            // Add new settings
            UserSettingsRepository.images.push({
                'positionId': image.positionId,
                'imageURL': image.imageURL,
                'top': image.top,
                'left': image.left,
                'scale': image.scaleX
            })
        }
    },

    getStoredImageSettings: function(pageId) {
        let result = [];
        for (let i = 0; i < UserSettingsRepository.images.length; i++) {
            let imagePositionId = UserSettingsRepository.images[i].positionId;
            let imagePageId = UserSettingsRepository._getPageIdFromPositionId(imagePositionId);
            if (imagePageId === pageId) {
                result.push(UserSettingsRepository.images[i]);
            }
        }
        return result;
    },

    removeStoredImageSettings: function(positionId) {
        for (let i = 0; i < UserSettingsRepository.images.length; i++) {
            if (UserSettingsRepository.images[i].positionId === positionId) {
                UserSettingsRepository.images.splice(i, 1);
            }
        }
    },

    _getPageIdFromPositionId: function(positionId) {
        let parts = positionId.split(".");
        let productId = parts[0];
        let pageId = parts[1];
        return productId + "." + pageId;
    }
};

let Editor = {

    // Id of currently rendered page
    pageId: undefined,

    // Fabric's canvas object - current canvas object
    canvas: undefined,

    // Fabric's rect objects - current positions in canvas (rects where images can be placed)
    rects: [],

    // Fabric's images objects - currently loaded images in positions (one image per rect)
    images: [],

    // Configuration loaded from back end
    configuration: {
        "id": "P1",
        "name": "Calendar 01",
        "basePrice": 9.90,
        "pages":
            [{
                "id": "P1.P1",
                "desc": "January",
                "width": 440,
                "height": 1330,
                "background": "./img/calendar_02_2020_01.jpg",
                "positions":
                    [{
                        "id": "P1.P1.P1",
                        "top": 43,
                        "left": 35,
                        "width": 367,
                        "height": 342
                    },{
                        "id": "P1.P1.P2",
                        "top": 415,
                        "left": 35,
                        "width": 367,
                        "height": 342
                    }]
            },{
                "id": "P1.P2",
                "desc": "February",
                "width": 440,
                "height": 1330,
                "background": "./img/calendar_01_2020_02.jpg",
                "positions":
                    [{
                        "id": "P1.P2.P1",
                        "top": 43,
                        "left": 35,
                        "width": 367,
                        "height": 716
                    }]
            },{
                "id": "P1.P3",
                "desc": "March",
                "width": 440,
                "height": 1330,
                "background": "./img/calendar_01_2020_03.jpg",
                "positions":
                    [{
                        "id": "P1.P3.P1",
                        "top": 43,
                        "left": 35,
                        "width": 367,
                        "height": 716
                    }]
            }]
    },

    /**
     * Takes pageId and using configuration created canvas and appropriate image positions (rects).
     * @param pageId ID og page to be loaded, e.g.: "P1.P1" (Product1.Page1)
     */
    _createPage: function(pageId) {
        console.trace("test");
        let page = Editor._getPageConfigurationById(pageId);
        Editor.pageId = page.id;
        Editor._createCanvasDOMElement();
        Editor.canvas = Editor._createCanvasObject(page.width, page.height, page.background);
        Editor._createPagePositions(page.positions, page.width);
        Editor.canvas.renderAll();
    },

    /**
     * Destroy page canvas, DOM element and clear temp memory for all images and positions (images, rects, canvas).
     */
    destroyPage: function() {
        if (Editor.canvas !== undefined) Editor.canvas.dispose();
        Editor.pageId = undefined;
        Editor.canvas = undefined;
        Editor.rects = [];
        Editor.images = [];
        if (document.getElementById("canvas") !== null) document.getElementById("canvas").remove();
    },

    loadPage: function(pageId) {
        Editor.destroyPage();
        Editor._createPage(pageId);
        Editor.loadStoredImagesToPositions(pageId);

    },

    loadStoredImagesToPositions: function (pageId) {
        let images = UserSettingsRepository.getStoredImageSettings(pageId);
        for (let i = 0; i < images.length; i++) {
            let image = images[i];
            console.log("Loading image to position ", image);
            Editor._loadImageToPosition(image.positionId, image.top, image.left, image.scale, image.imageURL, false);
        }
    },

    getTotalPositions: function() {
        let totalPosition = 0;
        for (let i = 0; i < Editor.configuration.pages.length; i++) {
            totalPosition += Editor.configuration.pages[i].positions.length;
        }
        return totalPosition;
    },

    getPositionFilled: function() {
        return UserSettingsRepository.images.length;
    },

    _createCanvasDOMElement: function() {
        document.getElementById("canvas-wrapper").innerHTML = "<canvas id='canvas'></canvas>";
    },

    _createCanvasObject: function(width, height, background) {
        let canvas = new fabric.Canvas("canvas");
        canvas.setDimensions({
            height: height,
            width: document.getElementById("canvas-wrapper").offsetWidth
        });

        let center = canvas.getCenter();
        canvas.setBackgroundImage(background,
            canvas.renderAll.bind(canvas), {
                left: center.left,
                originX: 'center'
            }
        );

        return canvas;
    },

    _createPagePositions: function(positions, pageWidth) {
        for (let i = 0; i < positions.length; i++) {
            let left = positions[i].left + (Editor.canvas.getCenter().left - (pageWidth / 2));
            let position = new fabric.Rect({
                id: positions[i].id,
                top: positions[i].top,
                left: left,
                width: positions[i].width,
                height: positions[i].height,
                stroke: '#9d9d9d',
                lockMovementX: true,
                lockMovementY: true,
                lockRotation: true,
                lockScalingX: true,
                lockScalingY: true,
                hasRotatingPoint: false,
                fill: '#ccc',
                strokeWidth: 0,
                absolutePositioned: true,
                selectable: false
            });

            // When image is dropped to image position
            // !!! When position already contains image, and new one is dropped to position
            // it does not fire drop event, there must be aldo drop event on image itself
            position.on('drop', function(event) {
                let imageURL = event.e.dataTransfer.getData("URL");
                let positionId = event.target.id;
                let position = Editor._getPositionById(positionId);
                Editor._loadImageToPosition(positionId, position.top, position.left, position.scaleX, imageURL, true);
            });

            Editor.canvas.add(position);
            Editor.rects.push(position);
        }
    },

    /**
     * Loads image to position when image is dropped
     * @param positionId Position where image should be loaded
     * @param top Image top position
     * @param left Image left position
     * @param scale Image scaleX, scaleY
     * @param imageURL URL of image to be loaded to given position
     * @param scaleToWidth Whatever image should be scaled to position width
     */

    // this must be adjusted so it can accept position (rect) or stored image
    _loadImageToPosition: function(positionId, top, left, scale, imageURL, scaleToWidth) {


        fabric.Image.fromURL(imageURL, function(image) {
            // Remove previous image if exists
            Editor._removeImageFromPosition(positionId);

            let position = Editor._getPositionById(positionId);
            if (scaleToWidth) {
                image.scaleToWidth(position.width, true);
            } else {
                image.scaleX = scale;
                image.scaleY = scale;
            }
            console.log(position);
            image.clipPath = position;
            image.top = top;
            image.left = left;
            image.positionId = positionId;
            image.hasRotatingPoint = false;
            image.imageURL = imageURL;
            image.cornerStyle = 'cornerStyle2';
            image.setControlsVisibility({ mt: false, mb: false, mr: false, ml: false, bl: true, br: true, tl: true, tr: true, mtr: false });

            Editor.images.push(image);
            Editor.canvas.add(image);


            // When new image is dropped over old image -> add new image and remove old one
            image.on('drop', function(event) {
                let imageURL = event.e.dataTransfer.getData("URL");
                let positionId = event.target.positionId;
                let position = Editor._getPositionById(positionId);
                Editor._loadImageToPosition(positionId, position.top, position.left, position.scaleX, imageURL, true);
            });

            image.on('moving', function(options) {
                // console.log(options);
            });

            image.on('scaling', function(options) {
                // console.log(options);
            });
        });
    },

    _removeImageFromPosition: function(positionId) {
        let existingImage = Editor._getImageByPositionId(positionId);
        if (existingImage !== undefined) {
            // Remove object from canvas
            Editor.canvas.remove(existingImage);
            // Remove image from array of images I internally use
            for (let i = 0; i < Editor.images.length; i++) {
                if (Editor.images[i].positionId === positionId) {
                    Editor.images.splice(i, 1);
                }
            }
        }
    },

    _getPositionById(positionId) {
        for (let i = 0; i < Editor.rects.length; i++) {
            if (Editor.rects[i].id === positionId) {
                return Editor.rects[i];
            }
        }
    },

    _getPageConfigurationById(pageId) {
        for (let i = 0; i < Editor.configuration.pages.length; i++) {
            if (Editor.configuration.pages[i].id === pageId) {
                return Editor.configuration.pages[i];
            }
        }
    },

    _getImageByPositionId(positionId) {
        for (let i = 0; i < Editor.images.length; i++) {
            if (Editor.images[i].positionId === positionId) {
                return Editor.images[i];
            }
        }
    },
};

let EditorUI = {
    loadPageThumbnails: function() {
        for (let i = 0; i < Editor.configuration.pages.length; i++) {
            let page = Editor.configuration.pages[i];
            let buttonHtml = $("<button type='button' class='btn btn-secondary btn-change-page'></button>").html(page.desc).attr('data-page-id', page.id);
            $('#sidebar-right .sidebar-wrapper').append(buttonHtml);
        }
    },
};

(function() {
    Editor.loadPage("P1.P1");
    Editor.loadStoredImagesToPositions("P1.P1");
    EditorUI.loadPageThumbnails();

    Editor.canvas.add(new fabric.Text('GeeksforGeeks', {
        fill: 'green'
    }));

    // When page thumbnail on right bar is clicked -> reload canvas view
    $('.btn-change-page').on('click', function(event) {
        let pageId = $(this).data('page-id');
        UserSettingsRepository.storeImageSettings(Editor.images);
        Editor.loadPage(pageId);
    });
})();