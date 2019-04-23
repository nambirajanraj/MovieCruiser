import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MovieRouterModule} from '../movie/movie.router.module';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { HttpClientModule } from '@angular/common/http';
import {MovieService} from './movie.service';
import {MatCardModule} from '@angular/material/card';
import {FormsModule} from '@angular/forms';
import { ContainerComponent } from './components/container/container.component';
import { WatchListComponent } from './components/watch-list/watch-list.component';
import {MatButtonModule} from  '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { MovieDialogComponent } from './components/movie-dialog/movie-dialog.component';
import { SearchMovieComponent } from './components/search/search.component';
@NgModule({
  declarations: [ThumbnailComponent,SearchMovieComponent, ContainerComponent, WatchListComponent, TmdbContainerComponent, MovieDialogComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    MatDialogModule,
    MovieRouterModule,
    MatInputModule,
    FormsModule,
    MatButtonModule,
    MatSnackBarModule,
    MatCardModule
  ],
  providers:[MovieService],
  entryComponents:[MovieDialogComponent],
  exports:[ThumbnailComponent, ContainerComponent,SearchMovieComponent, WatchListComponent, TmdbContainerComponent, MovieDialogComponent]
})
export class MovieModule { }
