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

module.exports = "<!-- \n<div class=\"container\">\n  <button type=\"button\" class=\"btn btn-outline-primary mb-3 mr-2 btn-sm\" (click)=\"onSelectRecentlySubtitle()\">Recent</button>\n  <button type=\"button\" class=\"btn btn-outline-primary mb-3 mr-2 btn-sm\" [routerLink]=\"['/subtitle-view-mother']\">Mother View</button>\n  <button type=\"button\" class=\"btn btn-outline-primary mb-3 mr-2 btn-sm\" [routerLink]=\"['/subtitle-list']\">List</button>\n  \n  <div class=\"row\">\n    <div class=\"col\">\n      <label>Foreign subtitle</label>\n    <li class=\"list-group-item list-group-item-action \" *ngFor=\"let subtitle of foreignSubtitle;\"\n      [class.selected]=\"subtitle === selectedSubtitle\"\n      (click)=\"onClick(subtitle)\">\n      [{{subtitle.sttlNum}}] {{subtitle.sttlDesc}}\n    </li>\n    </div>\n    <div class=\"col\">\n      <label>Mother subtitle</label>\n      <li class=\"list-group-item list-group-item-action \" *ngFor=\"let subtitle of motherSubtitle;\"\n    [class.selected]=\"subtitle === selectedSubtitle\"\n    (click)=\"onClick(subtitle)\">\n    [{{subtitle.sttlNum}}] {{subtitle.sttlDesc}}\n  </li>\n    </div>\n  </div>\n</div> -->\n\n<div class=\"container\">\n\n<button type=\"button\" class=\"btn btn-outline-primary mb-3 mr-2 btn-sm\" (click)=\"onSelectRecentlySubtitle()\">Recent</button>\n<button type=\"button\" class=\"btn btn-outline-primary mb-3 mr-2 btn-sm\" [routerLink]=\"['/subtitle-view-mother']\">Mother View</button>\n<button type=\"button\" class=\"btn btn-outline-primary mb-3 mr-2 btn-sm\" [routerLink]=\"['/subtitle-list']\">List</button>\n\n<div class=\"chat-panel card\">\n  <div class=\"card-header\">\n     Foreign Subtitle\n  </div>\n  <div class=\"\">\n    <ul class=\"chat\">\n      <li class=\"clearfix\" *ngFor=\"let subtitle of foreignSubtitle;\"\n          [class.selected]=\"subtitle === selectedSubtitle\"\n          (click)=\"onClick(subtitle)\">\n          [{{subtitle.sttlNum}}] {{subtitle.sttlDesc}}\n      </li>\n    </ul>\n  </div>\n</div>\n\n<div class=\"mb-2\"></div>\n\n<div class=\"chat-panel card\">\n    <div class=\"card-header\">\n       Mother Subtitle\n    </div>\n    <div class=\"\">\n        <ul class=\"chat\">\n          <li class=\"clearfix\" *ngFor=\"let subtitle of motherSubtitle;\"\n              [class.selected]=\"subtitle === selectedSubtitle\"\n              (click)=\"onClick(subtitle)\">\n              [{{subtitle.sttlNum}}] {{subtitle.sttlDesc}}\n          </li>\n        </ul>\n      </div>\n  </div>\n</div>\n\n<!-- \n<li class=\"left clearfix\">\n    <span class=\"chat-img pull-left\">\n        <img src=\"http://placehold.it/50/55C1E7/fff\" alt=\"User Avatar\" class=\"img-circle\">\n    </span>\n    <div class=\"chat-body clearfix\">\n        <div class=\"header\">\n            <strong class=\"primary-font\">Jack Sparrow</strong>\n            <small class=\"pull-right text-muted\">\n                <i class=\"fa fa-clock-o fa-fw\"></i> 12 mins ago\n            </small>\n        </div>\n        <p>\n            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare dolor, quis ullamcorper ligula sodales.\n        </p>\n    </div>\n</li> -->"

/***/ }),

/***/ "./src/app/layout/subtitle/subtitle-view/subtitle-view.component.scss":
/*!****************************************************************************!*\
  !*** ./src/app/layout/subtitle/subtitle-view/subtitle-view.component.scss ***!
  \****************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".chat-panel .chat {\n  height: 300px;\n  overflow-y: auto;\n  margin-top: 15px;\n  margin-left: 15px;\n  margin-bottom: 15px;\n  padding: 0 0 0 0;\n  list-style: none; }\n  .chat-panel .chat li {\n    margin-bottom: 10px;\n    margin-right: 15px;\n    padding-bottom: 5px;\n    border-bottom: 1px dotted #999; }\n  .container {\n  min-height: 100vh; }\n  .li {\n  margin-bottom: 10px;\n  margin-right: 15px;\n  padding-bottom: 5px;\n  border-bottom: 1px dotted #999; }\n"

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




var SubtitleViewComponent = /** @class */ (function () {
    function SubtitleViewComponent(subtitleService, route) {
        this.subtitleService = subtitleService;
        this.route = route;
        this.subtitle = new _subtitle__WEBPACK_IMPORTED_MODULE_2__["Subtitle"]();
    }
    SubtitleViewComponent.prototype.ngOnInit = function () {
        this.usrId = localStorage.getItem('usrId');
        this.subtitle.usrId = this.usrId;
        console.log("this.subtitle.usrId==" + this.subtitle.usrId);
        this.onSelectUsrSttl();
    };
    //subtitle : Subtitle = [];
    //usrId = 'lifedomy@gmail.com';
    //usrId = localStorage.getItem('usrId');
    SubtitleViewComponent.prototype.onSelectUsrSttl = function () {
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
                //console.log(result.subtitleListVo);  
            }
        });
    };
    SubtitleViewComponent.prototype.onSelectRecentlySubtitle = function () {
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
    SubtitleViewComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-subtitle-view',
            template: __webpack_require__(/*! ./subtitle-view.component.html */ "./src/app/layout/subtitle/subtitle-view/subtitle-view.component.html"),
            styles: [__webpack_require__(/*! ./subtitle-view.component.scss */ "./src/app/layout/subtitle/subtitle-view/subtitle-view.component.scss")]
        }),
        __metadata("design:paramtypes", [_subtitle_service__WEBPACK_IMPORTED_MODULE_1__["SubtitleService"],
            _angular_router__WEBPACK_IMPORTED_MODULE_3__["ActivatedRoute"]])
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