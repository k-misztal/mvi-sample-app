package pl.misztal.template.di.module;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class SchedulerModule {

    public static final String MAIN_THREAD_SCHEDULER = "MAIN_THREAD_SCHEDULER";
    public static final String IO_SCHEDULER = "IO_SCHEDULER";
    public static final String COMPUTATION_SCHEDULER = "COMPUTATION_SCHEDULER";

    @Provides
    @Named(MAIN_THREAD_SCHEDULER)
    Scheduler provideMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Named(IO_SCHEDULER)
    Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Named(COMPUTATION_SCHEDULER)
    Scheduler provideComputationScheduler() {
        return Schedulers.computation();
    }
}
