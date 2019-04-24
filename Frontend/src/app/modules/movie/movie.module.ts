import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MovieRouterModule} from '../movie/movie.router.module';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { HttpClientModule } from '@angular/common/http';
import {MovieService} from './movie.service';
import { ContainerComponent } from './components/container/container.component';
import { WatchListComponent } from './components/watch-list/watch-list.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { MovieDialogComponent } from './components/movie-dialog/movie-dialog.component';
import { SearchMovieComponent } from './components/search/search.component';
import { SharedModule } from '../shared/shared.module';
import {TokenInterceptorService} from './token-interceptor.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
@NgModule({
  declarations: [ThumbnailComponent,SearchMovieComponent, ContainerComponent, WatchListComponent, TmdbContainerComponent, MovieDialogComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    MovieRouterModule,
    SharedModule
    
  ],
  providers:[MovieService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }
  
  ],
  entryComponents:[MovieDialogComponent],
  exports:[ThumbnailComponent, ContainerComponent,SearchMovieComponent, WatchListComponent, TmdbContainerComponent, MovieDialogComponent]
})
export class MovieModule { }
