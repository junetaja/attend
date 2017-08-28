package id.co.uti.utiattendance.module.startup;

import dagger.Component;
import id.co.uti.utiattendance.module.startup.activity.StartupActivity;
import zambelz.library.core.components.MainApplicationComponent;
import zambelz.library.core.modules.ActivityModule;
import zambelz.library.core.scopes.AppScope;

/**
 * Created by DELL on 8/22/2017.
 */


@AppScope
@Component(
        modules = {
                StartupModule.class,
                ActivityModule.class
        },
        dependencies = MainApplicationComponent.class
)

public interface StartupComponent {
    void inject(StartupActivity startupActivity);
}
