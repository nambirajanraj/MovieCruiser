import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { ContainerComponent } from './components/container/container.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { WatchListComponent } from  './components/watch-list/watch-list.component';
import { SearchMovieComponent } from './components/search/search.component';
import { AuthGuardService } from '../../auth-guard.service';

const movieRoutes: Routes = [
  {
    path: 'movies',
    children: [
      {
        path: '',
        redirectTo: '/movies/popular',
        pathMatch: 'full',
        canActivate: [AuthGuardService]
      },
      {
        path: 'popular',
        component: TmdbContainerComponent,
        canActivate: [AuthGuardService],
        data: {
          movieType: 'popular'
        },
      },
      {
        path: 'top_rated',
        canActivate: [AuthGuardService],
        component: TmdbContainerComponent,
        data: {
          movieType: 'top_rated'
        }
      },
      {
        path:'watchList',
        canActivate: [AuthGuardService],
        component:WatchListComponent,
      },
      {
        path:'search',
        canActivate: [AuthGuardService],
        component:SearchMovieComponent,
      }
    ]
  }
]

@NgModule({
  imports: [RouterModule.forRoot(movieRoutes)],
  exports: [RouterModule]
})
export class MovieRouterModule { }
