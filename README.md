# android-playground
Playground for learning android components. This project is for personal development, to understand the Android framework's capabilities, and doesn't serve any useful purpose. I have attempted to adhere to the Material Design guidelines. However, note none of the screens have much design thought put into them, they just allow me to understand components that could be used to make good design.

I have attempted to create a base MVP Architecture which is a work in progress. There are 3 examples of this:
</br><b>MyFirstAttempt</b> - My initial attempt at creating a basic structure.
</br><b>Remind101ExampleAdapted</b> - An adaptation of an example from http://engineering.remind.com/android-code-that-scales/ which integrates MVP in list structures and considers ViewHolders to be Views. See Movies for basic usage.
</br><b>RxMvp</b> - Finally I've started creating one using Rx which I'll continue building as I understand Rx. See Ethereum and Signup for basic usage.

I've played with RxAndroid (see Signup), Retrofit (see Network) and Picasso (see Network/utils) here at a basic level as well as many fundamental Android components. I've started playing with Dagger (see App and Soquestions) and doing some unit tests (see Movies) which I intend on adding throughout the code.

Note the app requires an API Key from the gradle.properties stored locally. If you want to build the app either add a TheMovieDb API key to a field 'theMovieDbApiKey' in your local gradle.properties or remove the buildConfigField for THE_MOVIE_DB_API_KEY in the app's build.gradle and set the THE_MOVIE_DB_API_KEY in ApiUris to empty (Note Api requests to the movie DB will not work in this case).

