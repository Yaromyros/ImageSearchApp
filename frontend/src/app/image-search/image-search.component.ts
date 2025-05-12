import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';  
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-image-search',
  standalone: true,
  imports: [CommonModule],                    
  templateUrl: './image-search.component.html',
  styleUrls:   ['./image-search.component.css']
})
export class ImageSearchComponent {
  selectedFile: File | null = null;
  uploadMessage = '';
  matchingImages: string[] = [];
  noImagesFoundMessage: string = ''; 
  loading = false;

  private uploadUrl = `${environment.apiUrl}/api/v1/images/upload`;
  private searchUrl = `${environment.apiUrl}/api/v1/images/search`;

  constructor(private http: HttpClient) {}

  onFileSelected(evt: Event) {
    const inp = evt.target as HTMLInputElement;
    this.selectedFile = inp.files?.[0] ?? null;
    this.uploadMessage = '';
  }

  uploadImage() {
    if (!this.selectedFile) return;
    const form = new FormData();
    form.append('file', this.selectedFile);
  
    this.http
      .post(this.uploadUrl, form, { responseType: 'text' })
      .subscribe({
        next: filename => {
          this.uploadMessage = '✅ Image uploaded successfully!';
          this.selectedFile = null;
        },
        error: err => {
          console.error('Upload error:', err);
          this.uploadMessage = '❌ Upload failed. Please try again.';
        }
      });
  }  

  searchImages(term: string) {
    const q = term.trim();
    if (!q) return;
  
    this.uploadMessage = '';
    this.matchingImages = [];
    this.noImagesFoundMessage = '';  
    this.loading = true;
  
    this.http
      .get<string[]>(`${this.searchUrl}?searchTerm=${encodeURIComponent(q)}`)
      .subscribe({
        next: keys => {
          console.log(keys); 
          if (keys.length === 0) {
            this.noImagesFoundMessage = '❌ No matching images found.'; 
          } else {
            this.matchingImages = keys;
          }
          this.loading = false;
        },
        error: () => this.uploadMessage = '❌ Search failed. Try again.'
      });
  }
}
