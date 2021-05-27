package com.fariq.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.fariq.moviecatalogue.R
import com.fariq.moviecatalogue.utils.DataDummy
import com.fariq.moviecatalogue.utils.EspressoIdlingResource
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {
    private val dummyMovie = DataDummy.generatesRemoteDummyMovies()
    private val dummyTvShow = DataDummy.generatesRemoteDummyTVShows()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadTvShows() {
        Espresso.onView(ViewMatchers.withText("TV Show")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.desc)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.desc)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    dummyMovie[0].desc
                )
            ))
    }

    @Test
    fun loadDetailTvShow() {
        Espresso.onView(ViewMatchers.withText("TV Show")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.desc)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.desc)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    dummyTvShow[0].desc
                )
            ))
    }

    @Test
    fun loadFavoriteMovies() {
        Espresso.onView(ViewMatchers.withId(R.id.action_favorites)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadFavoriteTvShows() {
        Espresso.onView(ViewMatchers.withId(R.id.action_favorites)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("TV Show")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }
}