package com.example.xcs.xcsdemo.rx;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;

import com.example.xcs.xcsdemo.R;

import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

/**
 * Created by Xcs on 2018-04-02.
 */

public abstract class BaseFragment extends Fragment {
    protected Disposable disposable;

    @OnClick(R.id.tipBt)
    void tip(){
        new AlertDialog.Builder(getActivity())
                .setTitle(getTitleRes())
                .setView(getActivity().getLayoutInflater().inflate(getDialogRes(),null))
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unSubscribe();
    }

    protected void unSubscribe(){
        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    protected abstract int getDialogRes();

    protected abstract int getTitleRes();
}
