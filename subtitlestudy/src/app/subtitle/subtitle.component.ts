import { Component, OnInit } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-subtitle',
  templateUrl: './subtitle.component.html',
  styleUrls: ['./subtitle.component.css']
})
export class SubtitleComponent implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  usrId = 'lifedomy@gmail.com';
  foreignFile = null;
  foreignFileNm = "Choose File";
  motherFile = null;
  motherFileNm = "Choose File";

  onForeignFileSelected(event) {
    this.foreignFile = <File> event.target.files[0];
    //console.log(this.foreignFile.name); 
    this.foreignFileNm = this.foreignFile.name;
  }

  onMotherFileSelected(event) {
    this.motherFile = <File> event.target.files[0];
    //console.log(this.motherFile.name); 
    this.motherFileNm = this.motherFile.name;
  }

  onUpload() {
    const fd = new FormData();
    fd.append('uploadFile', this.foreignFile);
    //study english using sutitles (foreign and self)
    this.http.post('http://localhost:8085/subtitle/saveUsrSubtitles',fd)
    //this.http.post('http://localhost:8080/dams/code/selectCodeExtList?searchValue=',fd)
    .subscribe(res => {
      console.log(res);
    });    
  }

}
