(function() {

    /**
     * Set clicked tab active and show tab's content
     */
    $(".sidebar-actions-bar").on("click", "li", function(event) {
        // Remove active class from active element
        $(".sidebar-actions-bar li.active").removeClass("active");
        $(this).addClass("active");

        // Hide all tabs and show only one
        let tabId = $(this).data("target-tab");
        $(".sidebar-content .content").hide();
        $(".sidebar-content #" + tabId).show(0);
    });
})();