(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["subtitle-subtitle-site-subtitle-site-module"],{

/***/ "./src/app/layout/subtitle/subtitle-site/subtitle-site-routing.module.ts":
/*!*******************************************************************************!*\
  !*** ./src/app/layout/subtitle/subtitle-site/subtitle-site-routing.module.ts ***!
  \*******************************************************************************/
/*! exports provided: SubtitleSiteRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SubtitleSiteRoutingModule", function() { return SubtitleSiteRoutingModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _subtitle_site_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./subtitle-site.component */ "./src/app/layout/subtitle/subtitle-site/subtitle-site.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



var routes = [
    {
        path: '',
        component: _subtitle_site_component__WEBPACK_IMPORTED_MODULE_2__["SubtitleSiteComponent"],
    }
];
var SubtitleSiteRoutingModule = /** @class */ (function () {
    function SubtitleSiteRoutingModule() {
    }
    SubtitleSiteRoutingModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"].forChild(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]]
        })
    ], SubtitleSiteRoutingModule);
    return SubtitleSiteRoutingModule;
}());



/***/ }),

/***/ "./src/app/layout/subtitle/subtitle-site/subtitle-site.component.html":
/*!****************************************************************************!*\
  !*** ./src/app/layout/subtitle/subtitle-site/subtitle-site.component.html ***!
  \****************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"container\">\n  <ul>\n    <li><a href=\"http://www.yifysubtitles.com/\" target=\"_blank\">yifysubtitles</a></li>\n  </ul>\n</div> "

/***/ }),

/***/ "./src/app/layout/subtitle/subtitle-site/subtitle-site.component.scss":
/*!****************************************************************************!*\
  !*** ./src/app/layout/subtitle/subtitle-site/subtitle-site.component.scss ***!
  \****************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/layout/subtitle/subtitle-site/subtitle-site.component.ts":
/*!**************************************************************************!*\
  !*** ./src/app/layout/subtitle/subtitle-site/subtitle-site.component.ts ***!
  \**************************************************************************/
/*! exports provided: SubtitleSiteComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SubtitleSiteComponent", function() { return SubtitleSiteComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _subtitle_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../subtitle.service */ "./src/app/layout/subtitle/subtitle.service.ts");
/* harmony import */ var _subtitle__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../subtitle */ "./src/app/layout/subtitle/subtitle.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var SubtitleSiteComponent = /** @class */ (function () {
    function SubtitleSiteComponent(subtitleService, route) {
        this.subtitleService = subtitleService;
        this.route = route;
        this.subtitle = new _subtitle__WEBPACK_IMPORTED_MODULE_2__["Subtitle"]();
        //subtitle : Subtitle = [];
        //usrId = 'lifedomy@gmail.com';
        //usrId = localStorage.getItem('usrId');
        this.foreignSubtitle = "";
        this.motherSubtitle = "";
    }
    SubtitleSiteComponent.prototype.ngOnInit = function () {
        this.usrId = localStorage.getItem('usrId');
        this.subtitle.usrId = this.usrId;
        console.log("this.subtitle.usrId==" + this.subtitle.usrId);
        this.onSelectUsrSttl();
    };
    SubtitleSiteComponent.prototype.onSelectUsrSttl = function () {
        var _this = this;
        var sttlNm = this.route.snapshot.paramMap.get('sttlNm');
        console.log("sttlNm==" + sttlNm);
        console.log("usrId1==" + localStorage.getItem('usrId'));
        console.log("usrId2==" + this.usrId);
        this.subtitle.usrId = this.usrId;
        this.subtitle.sttlNm = sttlNm;
        this.subtitleService.selectUsrSttl(this.subtitle)
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
    SubtitleSiteComponent.prototype.onSelectRecentlySubtitle = function () {
        var _this = this;
        this.subtitle.usrId = this.usrId;
        this.subtitleService.selectRecentlySubtitle(this.subtitle)
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
    SubtitleSiteComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-subtitle-site',
            template: __webpack_require__(/*! ./subtitle-site.component.html */ "./src/app/layout/subtitle/subtitle-site/subtitle-site.component.html"),
            styles: [__webpack_require__(/*! ./subtitle-site.component.scss */ "./src/app/layout/subtitle/subtitle-site/subtitle-site.component.scss")]
        }),
        __metadata("design:paramtypes", [_subtitle_service__WEBPACK_IMPORTED_MODULE_1__["SubtitleService"],
            _angular_router__WEBPACK_IMPORTED_MODULE_3__["ActivatedRoute"]])
    ], SubtitleSiteComponent);
    return SubtitleSiteComponent;
}());



/***/ }),

/***/ "./src/app/layout/subtitle/subtitle-site/subtitle-site.module.ts":
/*!***********************************************************************!*\
  !*** ./src/app/layout/subtitle/subtitle-site/subtitle-site.module.ts ***!
  \***********************************************************************/
/*! exports provided: SubtitleSiteModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SubtitleSiteModule", function() { return SubtitleSiteModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _subtitle_site_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./subtitle-site.component */ "./src/app/layout/subtitle/subtitle-site/subtitle-site.component.ts");
/* harmony import */ var _subtitle_site_routing_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./subtitle-site-routing.module */ "./src/app/layout/subtitle/subtitle-site/subtitle-site-routing.module.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};




var SubtitleSiteModule = /** @class */ (function () {
    function SubtitleSiteModule() {
    }
    SubtitleSiteModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"], _subtitle_site_routing_module__WEBPACK_IMPORTED_MODULE_3__["SubtitleSiteRoutingModule"]
            ],
            declarations: [_subtitle_site_component__WEBPACK_IMPORTED_MODULE_2__["SubtitleSiteComponent"]]
        })
    ], SubtitleSiteModule);
    return SubtitleSiteModule;
}());



/***/ })

}]);
//# sourceMappingURL=subtitle-subtitle-site-subtitle-site-module.js.map