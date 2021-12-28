class DocumentConfig {
    id;
    name;
    canvases = [];

    constructor(id, name, pages) {
        this.id = id;
        this.name = name;
        this.canvases = pages;
    }

    getCanvases() {
        return this.canvases;
    }

    getCanvas(canvasId) {
        for (let i = 0; i < this.canvases.length; i++) {
           let canvas = this.canvases[i];
           if (canvas.id === canvasId) {
               return canvas;
           }
        }
    }

    getRect(rectId) {
        for (let i = 0; i < this.canvases.length; i++) {
            let canvasConfig = this.canvases[i];
            for (let j = 0; j < canvasConfig.getRects().length; j++) {
                let rectConfig = canvasConfig.getRects()[j];
                if (rectConfig.getId() === rectId) {
                    return rectConfig;
                }
            }
        }
    }
}

class CanvasConfig {
    id;
    width;
    height;
    background;
    rects = [];

    constructor(id, width, height, background, rects) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.background = background;
        this.rects = rects;
    }

    getId() {
        return this.id;
    }

    getWidth() {
        return this.width;
    }

    getHeight() {
        return this.height;
    }

    getBackground() {
        return this.background;
    }

    getRects() {
        return this.rects;
    }

    getRect(rectId) {
        for (let i = 0; i < this.rects.length; i++) {
            let rect = this.rects[i];
            if (rect.id === id) {
                return rect;
            }
        }
    }
}

class RectConfig {
    id;
    top;
    left;
    width;
    height;
    background;

    constructor(id, top, left, width, height, background) {
        this.id = id;
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
        this.background = background;
    }

    getId() {
        return this.id;
    }

    getTop() {
        return this.top;
    }

    getLeft() {
        return this.left;
    }

    getWidth() {
        return this.width;
    }

    getHeight() {
        return this.height;
    }

    getBackground() {
        return this.background;
    }
}

class RectBackgroundConfig {
    imageURL;
    scale;
    offsetX;
    offsetY;

    constructor(imageURL, scale, offsetX, offsetY) {
        this.imageURL = imageURL;
        this.scale = scale;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    getImageURL() {
        return this.imageURL;
    }

    getScale() {
        return this.scale;
    }

    getOffsetX() {
        return this.offsetX;
    }

    getOffsetY() {
        return this.offsetY;
    }
}

