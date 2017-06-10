package pl.misztal.template.di.component;

import dagger.Component;
import pl.misztal.template.di.scope.FragmentSingleton;

/**
 * Created by kmisztal on 10.06.2017.
 *
 * @author Krzysztof Misztal
 */
@FragmentSingleton
@Component(dependencies = ActivityComponent.class)
public interface FragmentComponent {
}
