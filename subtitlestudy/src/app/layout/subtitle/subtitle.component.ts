import { Component, OnInit } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-subtitle',
  templateUrl: './subtitle.component.html',
  styleUrls: ['./subtitle.component.scss']
})
export class SubtitleComponent implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  usrId = 'lifedomy@gmail.com';
  foreignFile = null;
  foreignFileNm = "Choose Srt or Smi File";
  motherFile = null;
  motherFileNm = "Choose Srt or Smi File";

  onForeignFileSelected(event) {
    this.foreignFile = <File> event.target.files[0];
    this.foreignFileNm = this.foreignFile.name;
    //alert(this.foreignFileNm.indexOf(".smi"));
    if(this.foreignFileNm.indexOf(".srt") == -1 && this.foreignFileNm.indexOf(".smi") == -1){
      alert("Please, choose srt,smi file only" );
      this.foreignFile = null;
      this.foreignFileNm = "Choose Srt or Smi File";
    }  
  }

  onMotherFileSelected(event) {
    this.motherFile = <File> event.target.files[0];
    this.motherFileNm = this.motherFile.name;
    if(this.motherFileNm.indexOf(".srt") == -1 || this.motherFileNm.indexOf(".smi") == -1){
      alert("Please, choose srt,smi file only" );
      this.motherFile = null;
      this.motherFileNm = "Choose Srt or Smi File";
    }
  }
    
  onUpload() {

    //alert("asdfasdfsadf");
    const fd = new FormData();
    fd.append('uploadFile', this.foreignFile);
    //study english using sutitles (foreign and self)
    this.http.post('http://localhost:8085/subtitle/test2',"")
    //this.http.post('http://localhost:8085/subtitle/saveUsrSubtitles',fd)
    //this.http.post('http://localhost:8085/dams/code/selectCodeExtList?searchValue=',fd)
    .subscribe(res => {
      console.log(res);
    });    
  }

} 