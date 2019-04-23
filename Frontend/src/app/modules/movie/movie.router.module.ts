import {NgModule} from '@angular/core';
import {RouterModule , Routes} from '@angular/router';
import { ContainerComponent } from './components/container/container.component';
import {TmdbContainerComponent} from './components/tmdb-container/tmdb-container.component';
import { WatchListComponent } from './components/watch-list/watch-list.component';
import {SearchMovieComponent} from './components/search/search.component';
 const movieRoutes: Routes= [
    {
        path:'movies',
        children: [
            {
                path:'',
                redirectTo: '/movies/popular',
                pathMatch: 'full'
            },
            {
                path:'popular',
                component: TmdbContainerComponent,
                data :{
                    movieType: 'popular'
                }
            },
            {
                path:'top_rated',
                component: TmdbContainerComponent,
                data :{
                    movieType: 'top_rated'
                }
            },
            {
               path: 'watchList',
               component: WatchListComponent,
            },
            {
               path: 'search',
               component: SearchMovieComponent,
            }

        ]
    }
];

@NgModule({
   imports:[
       RouterModule.forChild(movieRoutes),
   ],
   exports: [
       RouterModule,
   ]

})
export class MovieRouterModule{}
