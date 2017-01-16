# android-playground
Playground for learning android components. This project is for personal development, to understand the Android framework's capabilities, and doesn't serve any useful purpose. I have attempted to adhere to the Material Design guidelines. However, note none of the screens have much design thought put into them, they just allow me to understand components that could be used to make good design.

I have attempted to create and adapt some base MVP structures. There are multiple examples of this:
</br><b>MyFirstAttempt</b> - My initial attempt at creating a basic structure.
</br><b>Remind101ExampleAdapted</b> - An adaptation of an example from http://engineering.remind.com/android-code-that-scales/ which integrates MVP in list structures and considers ViewHolders to be Views. See Movies for basic usage.
</br><b>RxMvp</b> - One using Rx, which holds observables and cancels them all together. See Ethereum and Signup for basic usage.
</br><b>BasicMvp</b> - A completely stripped back basic approach adapted from https://github.com/googlesamples/android-architecture which I expect to continue using.

I've played with RxJava (see Signup), Retrofit (see Network) and Picasso (see Network/utils) here at a basic level as well as many fundamental Android components. I've started playing with Dagger (see App and Soquestions) and doing some unit and automation testing (see Movies). I've also introduced a Realm DB caching structure (see Sorealm).

Note the app requires an API Key from the gradle.properties stored locally. If you want to build the app either add a TheMovieDb API key to a field 'theMovieDbApiKey' in your local gradle.properties or remove the buildConfigField for THE_MOVIE_DB_API_KEY in the app's build.gradle and set the THE_MOVIE_DB_API_KEY in ApiUris to empty (Note Api requests to the movie DB will not work in this case).

