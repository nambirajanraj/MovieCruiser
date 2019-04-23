import { Injectable } from '@angular/core';
import {map}  from 'rxjs/operators';
import {retry} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import { Movie} from './movie';
import {Observable} from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  tmdbEndpoint: string;
  imagePrefix : string;
  apiKey: string;
  watchListEndPoint: string;
  springEndPoint: string;

  constructor(private http: HttpClient) {
    this.apiKey = "api_key=58ca98304c584a5cb247b67e8276c76d";
    this.tmdbEndpoint ="https://api.themoviedb.org/3";
    
    this.imagePrefix ="https://image.tmdb.org/t/p/w500/";
    this.watchListEndPoint="http://localhost:3000/watchList"
    this.springEndPoint="http://localhost:8888/api/movie";

 }

   getMovies(type:string , page:number=1):Observable<Array<Movie>>
   {
       const endpoint=`${this.tmdbEndpoint}/movie/${type}?${this.apiKey}&page=${page}`;
       return this.http.get(endpoint).pipe(
        retry(3),
        map(this.pickMovieResults),
        map(this.transformPosterPath.bind(this))
      );
   }
  
  
  transformPosterPath(movies): Observable<Array<Movie>>
  {
      movies.map(movie =>{
        movie.poster_path = `${this.imagePrefix}${movie.poster_path}`;
           return movie;
       });
       return movies;
  }

  pickMovieResults(response)
  {
    return response['results'];
  }

  addMovieToWatchList(movie)
  {
      return this.http.post(this.springEndPoint,movie);
  }

   getWatchListMovies():Observable<Array<Movie>>{
       return this.http.get<Array<Movie>>(this.springEndPoint);
   }
   
   deleteFromMyWatchList(movie:Movie){
    const url= `${this.springEndPoint}/${movie.id}`;
    console.log(url);
    return this.http.delete(url,{responseType:'text'});
  }

  updateWatchlist(movie) {
    const url= `${this.springEndPoint}/${movie.id}`;
    return this.http.put(url, movie);
   }
   
   searchMovie(searchKey: string,page: number = 1): Observable<Array<Movie>> {
    if (searchKey.length > 0) {
      const searchEndpoint = `${this.tmdbEndpoint}/search/movie?${this.apiKey}&page=${page}&include_adult=false&query=${searchKey}`;
      return this.http.get(searchEndpoint).pipe(
        
        map(this.pickMovieResults),
        map(this.transformPosterPath.bind(this))
      );
    }
  }
  
  

}
