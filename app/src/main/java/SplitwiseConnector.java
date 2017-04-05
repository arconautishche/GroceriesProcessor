import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.os.Bundle;

import java.io.IOException;

/**
 * Created by anton on 28-Mar-17.
 */

public class SplitwiseConnector {

    public void TestConnection(Context context)
    {

        AccountManager am = AccountManager.get(context);
    }

    private class OnTokenAcquired implements AccountManagerCallback<Bundle>{
        @Override
        public void run(AccountManagerFuture<Bundle> result) {
            Bundle bundle = null;
            try {
                bundle = result.getResult();
            } catch (OperationCanceledException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (AuthenticatorException e) {
                e.printStackTrace();
            }

            String token = bundle.getString(AccountManager.KEY_AUTHTOKEN);

        }
    }
}
