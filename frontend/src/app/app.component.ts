import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ImageSearchComponent } from './image-search/image-search.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, ImageSearchComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
}
