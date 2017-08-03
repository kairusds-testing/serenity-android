package us.nineworlds.serenity.jobs;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.birbit.android.jobqueue.RetryConstraint;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import us.nineworlds.plex.rest.PlexappFactory;
import us.nineworlds.plex.rest.model.impl.MediaContainer;
import us.nineworlds.serenity.events.MovieSecondaryCategoryEvent;
import us.nineworlds.serenity.injection.InjectingJob;

public class MovieSecondaryCategoryJob extends InjectingJob {

  @Inject PlexappFactory client;

  @Inject EventBus eventBus;

  String key;
  String category;

  public MovieSecondaryCategoryJob(String key, String category) {
    this.key = key;
    this.category = category;
  }

  @Override public void onAdded() {

  }

  @Override public void onRun() throws Throwable {
    MediaContainer mediaContainer = client.retrieveSections(key, category);
    eventBus.post(new MovieSecondaryCategoryEvent(mediaContainer, key, category));
  }

  @Override protected void onCancel(int cancelReason, @Nullable Throwable throwable) {

  }

  @Override
  protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount,
      int maxRunCount) {
    return null;
  }
}
