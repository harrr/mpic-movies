package algo;

import models.Movie;

import java.util.List;
import java.util.Random;

/**
 * Created by vitaly on 4/9/14.
 */
public class PreferencesAlgo {

    public Movie getForRating(List<Movie> candidates, List<Movie> watched) {
        //TODO: get random movie by from non watched candidates
        for(Movie movie:watched){
            int iWatched = candidates.indexOf(movie);
            if(iWatched>=0)
                candidates.remove(iWatched);
        }
        Random random = new Random();
        int randomNum = random.nextInt(candidates.size());
        return candidates.get(randomNum);
    }
}
