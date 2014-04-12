package algo;

import models.Movie;
import models.Rating;
import models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by vitaly on 4/9/14.
 */
public class SuggestionsAlgo {
    private final int RATING_VALUES_COUNT = 10;

    private int nUsers;     //number of users
    private int nRatingValues;    //number of ratings, size of rating count matrix
    private double[][] similarityValues;
    private List<User> allUsers; //not initialized

    public List<Movie> getRecommended(User user, Map<User, List<Rating>> ratings, int limit) {
        List<Movie> recommendations = new ArrayList<Movie>();
        List<Movie> notRated = null; //list of movies not rated by the user
        for (Movie movie : notRated)
        {
            double predictedRating = PredictRating(user, movie);
            if (!Double.isNaN(predictedRating)){
                recommendations.add(movie);
            }
        }

        return recommendations.subList(0,limit-1);
    }

    private double PredictRating(User user, Movie movie) {
        double predictedRating = Double.NaN;
        double similaritySum = 0.0;
        double weightRatingSum = 0.0;
        for (User anotherUser:allUsers){
            double ratingByUser = 0; //rating by the user nedeed
            if (ratingByUser != 0){   //if item was rated
                double similarityBetweenUsers = similarityValues[allUsers.indexOf(user)][ allUsers.indexOf(anotherUser)];
                double ratingByAnotherUser = 0; //rating by another user nedeed
                double weigthRating = similarityBetweenUsers*ratingByAnotherUser;
                weightRatingSum += weigthRating;
                similaritySum += similarityBetweenUsers;
            }
            if (similaritySum > 0.0){
                predictedRating = weightRatingSum/similaritySum;
            }
        }
        return predictedRating;
    }

    private void CalculateUserSimilarity(List<User> users, List<Rating> ratingsByUserA){
        nUsers = users.size();
        nRatingValues = RATING_VALUES_COUNT;
        similarityValues = new double[nUsers][nUsers];
        RatingCountMatrix rcm;
        for (int u = 0; u<nUsers; u++){
            for (int v=u+1; v<nUsers; v++){
                List<Rating> ratingsByUserB = null;  //initialize list of ratings by user
                rcm = new RatingCountMatrix(ratingsByUserA, ratingsByUserB, nRatingValues);
                int totalCount = rcm.GetTotalCount();
                int agreementCount = rcm.GetAgreementCount();
                if (agreementCount>0){
                    similarityValues[u][v] = (double)agreementCount/(double)totalCount;
                }
                else {
                    similarityValues[u][v] = 0.0;
                }
            }
            similarityValues[u][u]=1.0;
        }
    }



}
