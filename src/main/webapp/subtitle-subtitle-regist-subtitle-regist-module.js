(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["subtitle-subtitle-regist-subtitle-regist-module"],{

/***/ "./src/app/layout/subtitle/subtitle-regist/subtitle-regist-routing.module.ts":
/*!***********************************************************************************!*\
  !*** ./src/app/layout/subtitle/subtitle-regist/subtitle-regist-routing.module.ts ***!
  \***********************************************************************************/
/*! exports provided: SubtitleRegistRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SubtitleRegistRoutingModule", function() { return SubtitleRegistRoutingModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _subtitle_regist_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./subtitle-regist.component */ "./src/app/layout/subtitle/subtitle-regist/subtitle-regist.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



var routes = [
    {
        path: '',
        component: _subtitle_regist_component__WEBPACK_IMPORTED_MODULE_2__["SubtitleRegistComponent"],
    }
];
var SubtitleRegistRoutingModule = /** @class */ (function () {
    function SubtitleRegistRoutingModule() {
    }
    SubtitleRegistRoutingModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"].forChild(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]]
        })
    ], SubtitleRegistRoutingModule);
    return SubtitleRegistRoutingModule;
}());



/***/ }),

/***/ "./src/app/layout/subtitle/subtitle-regist/subtitle-regist.component.html":
/*!********************************************************************************!*\
  !*** ./src/app/layout/subtitle/subtitle-regist/subtitle-regist.component.html ***!
  \********************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"container\">\n  <form>\n    <div class=\"form-group\">\n      <label for=\"inputAddress\">외국어 자막과 모국어 자막을 합성하여 출력합니다</label>\n    </div>\n    <div class=\"form-row\">\n      <div class=\"col-md-6\">\n        <label for=\"inputAddress\">Foreign Language</label>\n      </div>\n      <div class=\"custom-file\">\n        <input type=\"file\" class=\"custom-file-input\" name=\"foreignFile\" (change)=\"onForeignFileSelected($event)\">\n        <label class=\"custom-file-label\" for=\"customFile\">{{foreignFileNm}}</label>\n      </div>\n    </div>\n    <div class=\"form-row\">\n      <div class=\"col-md-6\">\n        <label for=\"inputAddress\">Mother Language</label>\n      </div>\n      <div class=\"custom-file\">\n        <input type=\"file\" class=\"custom-file-input\"  name=\"motherFile\" (change)=\"onMotherFileSelected($event)\">\n        <label class=\"custom-file-label\" for=\"customFile\">{{motherFileNm}}</label>\n      </div>\n    </div>\n    <div class=\"form-row\">\n      <button type=\"button\" class=\"btn btn-success\" (click)=\"onUpload()\">Upload</button> \n      <button type=\"button\" class=\"btn btn-success\" (click)=\"onSelectRecentlySubtitle()\">Recently Subtitle</button>\n      <button type=\"button\" class=\"btn btn-success\" (click)=\"onTest()\">test1</button>\n      <button type=\"button\" class=\"btn btn-success\" (click)=\"onTest2()\">test2</button>\n      <button type=\"button\" class=\"btn btn-success\" (click)=\"onTest3()\">test2</button>\n      <button type=\"button\" class=\"btn btn-success\" (click)=\"onTest4()\">test4</button>\n    </div>\n    \n    <div class=\"form-row\">\n      <label>Foreign subtitle</label>\n      <textarea class=\"form-control\" rows=\"10\">{{foreignSubtitle}}</textarea>\n    </div>\n    <div class=\"form-row\">\n      <label>Mother subtitle</label>\n      <textarea class=\"form-control\" rows=\"10\">{{motherSubtitle}}</textarea>\n    </div> \n  </form>\n</div>"

/***/ }),

/***/ "./src/app/layout/subtitle/subtitle-regist/subtitle-regist.component.scss":
/*!********************************************************************************!*\
  !*** ./src/app/layout/subtitle/subtitle-regist/subtitle-regist.component.scss ***!
  \********************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/layout/subtitle/subtitle-regist/subtitle-regist.component.ts":
/*!******************************************************************************!*\
  !*** ./src/app/layout/subtitle/subtitle-regist/subtitle-regist.component.ts ***!
  \******************************************************************************/
/*! exports provided: SubtitleRegistComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SubtitleRegistComponent", function() { return SubtitleRegistComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _subtitle_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../subtitle.service */ "./src/app/layout/subtitle/subtitle.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var httpOptions = {
    headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpHeaders"]({ 'Content-Type': 'application/json' })
};
var SubtitleRegistComponent = /** @class */ (function () {
    function SubtitleRegistComponent(http, subtitleService) {
        this.http = http;
        this.subtitleService = subtitleService;
        this.usrId = 'lifedomy@gmail.com';
        this.foreignFile = null;
        this.foreignFileNm = "Choose Srt or Smi File";
        this.foreignSubtitle = "";
        this.motherFile = null;
        this.motherFileNm = "Choose Srt or Smi File";
        this.motherSubtitle = "";
    }
    SubtitleRegistComponent.prototype.ngOnInit = function () {
    };
    SubtitleRegistComponent.prototype.onForeignFileSelected = function (event) {
        this.foreignFile = event.target.files[0];
        this.foreignFileNm = this.foreignFile.name;
        //alert(this.foreignFileNm.indexOf(".smi"));
        if (this.foreignFileNm.indexOf(".srt") == -1 && this.foreignFileNm.indexOf(".smi") == -1) {
            alert("Please, choose srt,smi file only");
            this.foreignFile = null;
            this.foreignFileNm = "Choose Srt or Smi File";
        }
    };
    SubtitleRegistComponent.prototype.onMotherFileSelected = function (event) {
        this.motherFile = event.target.files[0];
        this.motherFileNm = this.motherFile.name;
        if (this.motherFileNm.indexOf(".srt") == -1 && this.motherFileNm.indexOf(".smi") == -1) {
            alert("Please, choose srt,smi file only");
            this.motherFile = null;
            this.motherFileNm = "Choose Srt or Smi File";
        }
    };
    SubtitleRegistComponent.prototype.onUpload = function () {
        var _this = this;
        var fd = new FormData();
        fd.append('uploadFile', this.foreignFile);
        fd.append('uploadFile2', this.motherFile);
        fd.append('usrEmail', "lifedomy@gmail.com");
        //study english using sutitles (foreign and self)
        this.subtitleService.saveUsrSubtitles(fd).subscribe(function (result) {
            if (!result.isSuccess)
                alert(result.errUsrMsg);
            else {
                _this.foreignSubtitle = result.foreignSubtitle;
                _this.motherSubtitle = result.motherSubtitle;
                console.log(result.usrMsg);
            }
        });
    };
    SubtitleRegistComponent.prototype.onSelectRecentlySubtitle = function () {
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
    SubtitleRegistComponent.prototype.getHeroes = function () {
        var _this = this;
        this.subtitleService.getHeroes()
            .subscribe(function (data) {
            _this.subtitle = data;
            console.log(data);
            console.log(_this.subtitle.name);
        });
    };
    SubtitleRegistComponent.prototype.onTest2 = function () {
        var _this = this;
        this.subtitleService.getHeroes()
            .subscribe(function (data) {
            _this.subtitle = data;
            console.log(data);
            console.log(_this.subtitle.name);
        });
    };
    SubtitleRegistComponent.prototype.onTest3 = function () {
        //this.getHeroes();
        //const params = new HttpParams().set('lang', lang); 
        var fd = new FormData();
        fd.append('name', "lifedomy");
        fd.append('id', "123");
        this.http.post('http://localhost:8085/subtitle/test4', fd)
            .subscribe(function (res) {
            console.log("1111111111111111111");
            console.log(res);
        });
    };
    SubtitleRegistComponent.prototype.onTest4 = function () {
        this.http.get('http://localhost:8085/subtitle/test4/?name=abc&id=10')
            .subscribe(function (res) {
            console.log("1111111111111111111");
            console.log(res);
        });
    };
    SubtitleRegistComponent.prototype.onTest = function () {
        this.getHeroes();
        // const fd = new FormData();
        // fd.append('uploadFile', this.foreignFile);
        // fd.append('uploadFile2', this.motherFile);
        // fd.append('usr', "lifedomy");
        // console.log("1111111111111111111");
        //console.log(this.http.get('http://localhost:8085/subtitle/test3',{responseType: 'json'}));
        // this.http.get('http://localhost:8085/subtitle/test3')
        // .subscribe(res => {
        //   console.log("1111111111111111111");
        //   console.log(res);
        //});   
        // this.http.post('http://localhost:8080/order/addtocart', 
        // '', 
        // httpOptions)
        // .subscribe(data => {
        //     // Handle the updated data here.
        //     console.log(data);
        // });
    };
    SubtitleRegistComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-subtitle-regist',
            template: __webpack_require__(/*! ./subtitle-regist.component.html */ "./src/app/layout/subtitle/subtitle-regist/subtitle-regist.component.html"),
            styles: [__webpack_require__(/*! ./subtitle-regist.component.scss */ "./src/app/layout/subtitle/subtitle-regist/subtitle-regist.component.scss")]
        }),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"],
            _subtitle_service__WEBPACK_IMPORTED_MODULE_2__["SubtitleService"]])
    ], SubtitleRegistComponent);
    return SubtitleRegistComponent;
}());



/***/ }),

/***/ "./src/app/layout/subtitle/subtitle-regist/subtitle-regist.module.ts":
/*!***************************************************************************!*\
  !*** ./src/app/layout/subtitle/subtitle-regist/subtitle-regist.module.ts ***!
  \***************************************************************************/
/*! exports provided: SubtitleRegistModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SubtitleRegistModule", function() { return SubtitleRegistModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _subtitle_regist_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./subtitle-regist.component */ "./src/app/layout/subtitle/subtitle-regist/subtitle-regist.component.ts");
/* harmony import */ var _subtitle_regist_routing_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./subtitle-regist-routing.module */ "./src/app/layout/subtitle/subtitle-regist/subtitle-regist-routing.module.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};




var SubtitleRegistModule = /** @class */ (function () {
    function SubtitleRegistModule() {
    }
    SubtitleRegistModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"], _subtitle_regist_routing_module__WEBPACK_IMPORTED_MODULE_3__["SubtitleRegistRoutingModule"]
            ],
            declarations: [_subtitle_regist_component__WEBPACK_IMPORTED_MODULE_2__["SubtitleRegistComponent"]]
        })
    ], SubtitleRegistModule);
    return SubtitleRegistModule;
}());



/***/ })

}]);
//# sourceMappingURL=subtitle-subtitle-regist-subtitle-regist-module.js.map