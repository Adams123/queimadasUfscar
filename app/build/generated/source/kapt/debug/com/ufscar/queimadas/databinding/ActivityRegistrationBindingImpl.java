package com.ufscar.queimadas.databinding;
import com.ufscar.queimadas.R;
import com.ufscar.queimadas.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityRegistrationBindingImpl extends ActivityRegistrationBinding  {

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
    }
    // views
    // variables
    // values
    // listeners
    private OnClickListenerImpl mRegistrationViewModelOnRegistrationButtonClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener emailInputandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of registrationViewModel.email
            //         is registrationViewModel.setEmail((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(emailInput);
            // localize variables for thread safety
            // registrationViewModel != null
            boolean registrationViewModelJavaLangObjectNull = false;
            // registrationViewModel.email
            java.lang.String registrationViewModelEmail = null;
            // registrationViewModel
            com.ufscar.queimadas.ui.welcome.registration.RegViewModel registrationViewModel = mRegistrationViewModel;



            registrationViewModelJavaLangObjectNull = (registrationViewModel) != (null);
            if (registrationViewModelJavaLangObjectNull) {




                registrationViewModel.setEmail(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener passwordInputandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of registrationViewModel.password
            //         is registrationViewModel.setPassword((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(passwordInput);
            // localize variables for thread safety
            // registrationViewModel != null
            boolean registrationViewModelJavaLangObjectNull = false;
            // registrationViewModel.password
            java.lang.String registrationViewModelPassword = null;
            // registrationViewModel
            com.ufscar.queimadas.ui.welcome.registration.RegViewModel registrationViewModel = mRegistrationViewModel;



            registrationViewModelJavaLangObjectNull = (registrationViewModel) != (null);
            if (registrationViewModelJavaLangObjectNull) {




                registrationViewModel.setPassword(((java.lang.String) (callbackArg_0)));
            }
        }
    };

    public ActivityRegistrationBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private ActivityRegistrationBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.EditText) bindings[2]
            , (androidx.constraintlayout.widget.Guideline) bindings[6]
            , (androidx.constraintlayout.widget.Guideline) bindings[5]
            , (android.widget.EditText) bindings[3]
            , (android.widget.ProgressBar) bindings[9]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (androidx.constraintlayout.widget.Guideline) bindings[8]
            , (android.widget.Button) bindings[1]
            , (androidx.constraintlayout.widget.Guideline) bindings[7]
            , (android.widget.ImageView) bindings[4]
            );
        this.emailInput.setTag(null);
        this.passwordInput.setTag(null);
        this.registrationLayout.setTag(null);
        this.signUpButton.setTag(null);
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
        if (BR.registrationViewModel == variableId) {
            setRegistrationViewModel((com.ufscar.queimadas.ui.welcome.registration.RegViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setRegistrationViewModel(@Nullable com.ufscar.queimadas.ui.welcome.registration.RegViewModel RegistrationViewModel) {
        this.mRegistrationViewModel = RegistrationViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.registrationViewModel);
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
        java.lang.String registrationViewModelEmail = null;
        com.ufscar.queimadas.ui.welcome.registration.RegViewModel registrationViewModel = mRegistrationViewModel;
        android.view.View.OnClickListener registrationViewModelOnRegistrationButtonClickAndroidViewViewOnClickListener = null;
        java.lang.String registrationViewModelPassword = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (registrationViewModel != null) {
                    // read registrationViewModel.email
                    registrationViewModelEmail = registrationViewModel.getEmail();
                    // read registrationViewModel::onRegistrationButtonClick
                    registrationViewModelOnRegistrationButtonClickAndroidViewViewOnClickListener = (((mRegistrationViewModelOnRegistrationButtonClickAndroidViewViewOnClickListener == null) ? (mRegistrationViewModelOnRegistrationButtonClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mRegistrationViewModelOnRegistrationButtonClickAndroidViewViewOnClickListener).setValue(registrationViewModel));
                    // read registrationViewModel.password
                    registrationViewModelPassword = registrationViewModel.getPassword();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.emailInput, registrationViewModelEmail);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.passwordInput, registrationViewModelPassword);
            this.signUpButton.setOnClickListener(registrationViewModelOnRegistrationButtonClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.emailInput, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, emailInputandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.passwordInput, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, passwordInputandroidTextAttrChanged);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.ufscar.queimadas.ui.welcome.registration.RegViewModel value;
        public OnClickListenerImpl setValue(com.ufscar.queimadas.ui.welcome.registration.RegViewModel value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onRegistrationButtonClick(arg0); 
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): registrationViewModel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}