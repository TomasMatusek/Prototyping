let documentConfig;
let canvasesRepository = new DocumentObjects();
let rectsRepository = new CanvasObjects();

(function() {

    /**
     * Settings parsed from JSON settings.
     * Holds only static data from database.
     */

    let backgroundConfig = new RectBackgroundConfig('http://localhost:9999/assets/img/drop.jpg', 100, 0, 0);

    let canvasPage1 = new CanvasConfig("P1.P1", 1500, 1333, 'http://localhost:9999/assets/img/calendar_01_2020_01.png', [
        new RectConfig("P1.P1.P1", 43, 35, 367, 342, backgroundConfig),
        new RectConfig("P1.P1.P2", 415, 35, 367, 342, backgroundConfig)
    ]);

    let canvasPage2 = new CanvasConfig("P1.P2", 440, 1333, 'http://localhost:9999/assets/img/calendar_02_2020_02.jpg', [
        new RectConfig("P1.P2.P1", 43, 35, 367, 342, backgroundConfig),
        new RectConfig("P1.P2.P2", 415, 35, 367, 342, backgroundConfig)
    ]);

    let canvasPage3 = new CanvasConfig("P1.P3", 440, 1333, 'http://localhost:9999/assets/img/calendar_02_2020_03.jpg', [
        new RectConfig("P1.P3.P1", 43, 35, 367, 342, backgroundConfig),
        new RectConfig("P1.P3.P2", 415, 35, 367, 342, backgroundConfig)
    ]);

    documentConfig = new DocumentConfig("P1", "Calendar 01", [canvasPage1, canvasPage2, canvasPage3]);

    /**
     * Creates and holds all instances of Fabric object's.
     * Hold instances so we can manipulate pages, positions, backgrounds.
     */

    canvasesRepository.addCanvases(documentConfig.getCanvases());
    rectsRepository.addRects(documentConfig.getCanvases());

})();