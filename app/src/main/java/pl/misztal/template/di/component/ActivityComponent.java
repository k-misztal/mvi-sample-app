package pl.misztal.template.di.component;

import dagger.Component;
import pl.misztal.template.di.scope.ActivitySingleton;

/**
 * Created by kmisztal on 10.06.2017.
 *
 * @author Krzysztof Misztal
 */
@ActivitySingleton
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {
}
