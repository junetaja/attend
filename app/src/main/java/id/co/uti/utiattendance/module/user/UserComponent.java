package id.co.uti.utiattendance.module.user;

import dagger.Component;
import id.co.uti.utiattendance.helper.HelperModule;
import id.co.uti.utiattendance.module.user.activity.LoginActivity;
import zambelz.library.core.components.MainApplicationComponent;
import zambelz.library.core.modules.ActivityModule;
import zambelz.library.core.scopes.AppScope;

/**
 * Created by DELL on 8/22/2017.
 */

@AppScope
@Component(
        modules = {
                UserModule.class,
                ActivityModule.class,
                HelperModule.class
        },
        dependencies = MainApplicationComponent.class
)

public interface UserComponent {
    void inject(LoginActivity loginActivity);
}
