/**
 * app.js - Common UI helpers for IPTS
 */

// ---- Auto-hide alert messages after 4 seconds ----
document.addEventListener("DOMContentLoaded", function () {
    var alerts = document.querySelectorAll(".alert");
    alerts.forEach(function (el) {
        setTimeout(function () {
            el.style.transition = "opacity 0.5s";
            el.style.opacity = "0";
            setTimeout(function () { el.remove(); }, 500);
        }, 4000);
    });

    // ---- Highlight active sidebar link ----
    var links = document.querySelectorAll(".sidebar a");
    var current = window.location.pathname;
    links.forEach(function (link) {
        if (link.href && link.href.indexOf(current) !== -1) {
            link.classList.add("active");
        }
    });

    // ---- Confirm on all delete buttons ----
    // (backup - JSP also has inline onclick confirm)
    document.querySelectorAll("[data-confirm]").forEach(function (el) {
        el.addEventListener("click", function (e) {
            if (!confirm(el.getAttribute("data-confirm"))) {
                e.preventDefault();
            }
        });
    });
});

/**
 * switchRole(role) - used on login page to toggle Admin/Student tabs
 */
function switchRole(role) {
    var roleField = document.getElementById("roleField");
    var tabAdmin  = document.getElementById("tabAdmin");
    var tabStu    = document.getElementById("tabStudent");
    if (!roleField) return;
    roleField.value        = role;
    tabAdmin.className     = (role === "admin")   ? "active" : "";
    tabStu.className       = (role === "student") ? "active" : "";
}
