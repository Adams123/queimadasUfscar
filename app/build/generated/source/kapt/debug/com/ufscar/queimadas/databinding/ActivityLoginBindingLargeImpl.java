package com.ufscar.queimadas.databinding;
import com.ufscar.queimadas.R;
import com.ufscar.queimadas.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityLoginBindingLargeImpl extends ActivityLoginBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.welcomeLogo, 4);
        sViewsWithIds.put(R.id.middle, 5);
        sViewsWithIds.put(R.id.left_margin, 6);
        sViewsWithIds.put(R.id.topMargin, 7);
        sViewsWithIds.put(R.id.right_margin, 8);
        sViewsWithIds.put(R.id.progressBar, 9);
        sViewsWithIds.put(R.id.orText, 10);
        sViewsWithIds.put(R.id.thirdPartyLogin, 11);
        sViewsWithIds.put(R.id.button, 12);
        sViewsWithIds.put(R.id.button2, 13);
    }
    // views
    // variables
    // values
    // listeners
    private OnClickListenerImpl mLoginViewModelOnLoginButtonClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener emailInputandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of loginViewModel.email
            //         is loginViewModel.setEmail((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(emailInput);
            // localize variables for thread safety
            // loginViewModel != null
            boolean loginViewModelJavaLangObjectNull = false;
            // loginViewModel.email
            java.lang.String loginViewModelEmail = null;
            // loginViewModel
            com.ufscar.queimadas.ui.welcome.login.LoginViewModel loginViewModel = mLoginViewModel;



            loginViewModelJavaLangObjectNull = (loginViewModel) != (null);
            if (loginViewModelJavaLangObjectNull) {




                loginViewModel.setEmail(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener passwordInputandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of loginViewModel.password
            //         is loginViewModel.setPassword((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(passwordInput);
            // localize variables for thread safety
            // loginViewModel != null
            boolean loginViewModelJavaLangObjectNull = false;
            // loginViewModel.password
            java.lang.String loginViewModelPassword = null;
            // loginViewModel
            com.ufscar.queimadas.ui.welcome.login.LoginViewModel loginViewModel = mLoginViewModel;



            loginViewModelJavaLangObjectNull = (loginViewModel) != (null);
            if (loginViewModelJavaLangObjectNull) {




                loginViewModel.setPassword(((java.lang.String) (callbackArg_0)));
            }
        }
    };

    public ActivityLoginBindingLargeImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private ActivityLoginBindingLargeImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[12]
            , (android.widget.Button) bindings[13]
            , (android.widget.EditText) bindings[2]
            , (androidx.constraintlayout.widget.Guideline) bindings[6]
            , (android.widget.Button) bindings[1]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (androidx.constraintlayout.widget.Guideline) bindings[5]
            , null
            , null
            , (android.widget.TextView) bindings[10]
            , (android.widget.EditText) bindings[3]
            , (android.widget.ProgressBar) bindings[9]
            , null
            , (androidx.constraintlayout.widget.Guideline) bindings[8]
            , (android.widget.LinearLayout) bindings[11]
            , (androidx.constraintlayout.widget.Guideline) bindings[7]
            , (android.widget.ImageView) bindings[4]
            );
        this.emailInput.setTag(null);
        this.loginBtn.setTag(null);
        this.loginLayout.setTag(null);
        this.passwordInput.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.loginViewModel == variableId) {
            setLoginViewModel((com.ufscar.queimadas.ui.welcome.login.LoginViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setLoginViewModel(@Nullable com.ufscar.queimadas.ui.welcome.login.LoginViewModel LoginViewModel) {
        this.mLoginViewModel = LoginViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.loginViewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        android.view.View.OnClickListener loginViewModelOnLoginButtonClickAndroidViewViewOnClickListener = null;
        java.lang.String loginViewModelEmail = null;
        java.lang.String loginViewModelPassword = null;
        com.ufscar.queimadas.ui.welcome.login.LoginViewModel loginViewModel = mLoginViewModel;

        if ((dirtyFlags & 0x3L) != 0) {



                if (loginViewModel != null) {
                    // read loginViewModel::onLoginButtonClick
                    loginViewModelOnLoginButtonClickAndroidViewViewOnClickListener = (((mLoginViewModelOnLoginButtonClickAndroidViewViewOnClickListener == null) ? (mLoginViewModelOnLoginButtonClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mLoginViewModelOnLoginButtonClickAndroidViewViewOnClickListener).setValue(loginViewModel));
                    // read loginViewModel.email
                    loginViewModelEmail = loginViewModel.getEmail();
                    // read loginViewModel.password
                    loginViewModelPassword = loginViewModel.getPassword();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.emailInput, loginViewModelEmail);
            this.loginBtn.setOnClickListener(loginViewModelOnLoginButtonClickAndroidViewViewOnClickListener);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.passwordInput, loginViewModelPassword);
        }
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.emailInput, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, emailInputandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.passwordInput, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, passwordInputandroidTextAttrChanged);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.ufscar.queimadas.ui.welcome.login.LoginViewModel value;
        public OnClickListenerImpl setValue(com.ufscar.queimadas.ui.welcome.login.LoginViewModel value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onLoginButtonClick(arg0); 
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): loginViewModel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}