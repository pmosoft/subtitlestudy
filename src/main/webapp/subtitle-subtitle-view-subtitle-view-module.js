(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["subtitle-subtitle-view-subtitle-view-module"],{

/***/ "./src/app/layout/subtitle/subtitle-view/subtitle-view-routing.module.ts":
/*!*******************************************************************************!*\
  !*** ./src/app/layout/subtitle/subtitle-view/subtitle-view-routing.module.ts ***!
  \*******************************************************************************/
/*! exports provided: SubtitleViewRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SubtitleViewRoutingModule", function() { return SubtitleViewRoutingModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _subtitle_view_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./subtitle-view.component */ "./src/app/layout/subtitle/subtitle-view/subtitle-view.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



var routes = [
    {
        path: '',
        component: _subtitle_view_component__WEBPACK_IMPORTED_MODULE_2__["SubtitleViewComponent"],
    }
];
var SubtitleViewRoutingModule = /** @class */ (function () {
    function SubtitleViewRoutingModule() {
    }
    SubtitleViewRoutingModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"].forChild(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]]
        })
    ], SubtitleViewRoutingModule);
    return SubtitleViewRoutingModule;
}());



/***/ }),

/***/ "./src/app/layout/subtitle/subtitle-view/subtitle-view.component.html":
/*!****************************************************************************!*\
  !*** ./src/app/layout/subtitle/subtitle-view/subtitle-view.component.html ***!
  \****************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"container\">\n  <form>\n    <div class=\"form-row\">\n      <button type=\"button\" class=\"btn btn-success\" (click)=\"onSelectRecentlySubtitle()\">Recently Subtitle</button>\n    </div>\n    <div class=\"form-row\">\n      <label>Foreign subtitle</label>\n      <textarea class=\"form-control\" rows=\"10\">{{foreignSubtitle}}</textarea>\n    </div>\n    <div class=\"form-row\">\n      <label>Mother subtitle</label>\n      <textarea class=\"form-control\" rows=\"10\">{{motherSubtitle}}</textarea>\n    </div> \n  </form>\n</div>"

/***/ }),

/***/ "./src/app/layout/subtitle/subtitle-view/subtitle-view.component.scss":
/*!****************************************************************************!*\
  !*** ./src/app/layout/subtitle/subtitle-view/subtitle-view.component.scss ***!
  \****************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/layout/subtitle/subtitle-view/subtitle-view.component.ts":
/*!**************************************************************************!*\
  !*** ./src/app/layout/subtitle/subtitle-view/subtitle-view.component.ts ***!
  \**************************************************************************/
/*! exports provided: SubtitleViewComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SubtitleViewComponent", function() { return SubtitleViewComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _subtitle_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../subtitle.service */ "./src/app/layout/subtitle/subtitle.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var SubtitleViewComponent = /** @class */ (function () {
    function SubtitleViewComponent(subtitleService) {
        this.subtitleService = subtitleService;
        this.usrId = 'lifedomy@gmail.com';
        this.foreignSubtitle = "";
        this.motherSubtitle = "";
    }
    SubtitleViewComponent.prototype.ngOnInit = function () {
    };
    SubtitleViewComponent.prototype.onSelectRecentlySubtitle = function () {
        var _this = this;
        this.subtitleService.selectRecentlySubtitle(this.usrId)
            .subscribe(function (result) {
            if (!result.isSuccess)
                alert(result.errUsrMsg);
            else {
                _this.foreignSubtitle = result.foreignSubtitle;
                _this.motherSubtitle = result.motherSubtitle;
                console.log(result.subtitleListVo);
            }
        });
    };
    SubtitleViewComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-subtitle-view',
            template: __webpack_require__(/*! ./subtitle-view.component.html */ "./src/app/layout/subtitle/subtitle-view/subtitle-view.component.html"),
            styles: [__webpack_require__(/*! ./subtitle-view.component.scss */ "./src/app/layout/subtitle/subtitle-view/subtitle-view.component.scss")]
        }),
        __metadata("design:paramtypes", [_subtitle_service__WEBPACK_IMPORTED_MODULE_1__["SubtitleService"]])
    ], SubtitleViewComponent);
    return SubtitleViewComponent;
}());



/***/ }),

/***/ "./src/app/layout/subtitle/subtitle-view/subtitle-view.module.ts":
/*!***********************************************************************!*\
  !*** ./src/app/layout/subtitle/subtitle-view/subtitle-view.module.ts ***!
  \***********************************************************************/
/*! exports provided: SubtitleViewModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SubtitleViewModule", function() { return SubtitleViewModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _subtitle_view_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./subtitle-view.component */ "./src/app/layout/subtitle/subtitle-view/subtitle-view.component.ts");
/* harmony import */ var _subtitle_view_routing_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./subtitle-view-routing.module */ "./src/app/layout/subtitle/subtitle-view/subtitle-view-routing.module.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};




var SubtitleViewModule = /** @class */ (function () {
    function SubtitleViewModule() {
    }
    SubtitleViewModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"], _subtitle_view_routing_module__WEBPACK_IMPORTED_MODULE_3__["SubtitleViewRoutingModule"]
            ],
            declarations: [_subtitle_view_component__WEBPACK_IMPORTED_MODULE_2__["SubtitleViewComponent"]]
        })
    ], SubtitleViewModule);
    return SubtitleViewModule;
}());



/***/ })

}]);
//# sourceMappingURL=subtitle-subtitle-view-subtitle-view-module.js.map