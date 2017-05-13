package ru.anit.weightcounting.ui.activityes;

import android.os.Bundle;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.anit.weightcounting.R;
import ru.anit.weightcounting.mvp.presenters.MainActivityPresenter;
import ru.anit.weightcounting.mvp.views.MainActivityView;

public class MainActivity extends BaseMvpActivity implements MainActivityView {

    @InjectPresenter
    MainActivityPresenter mPresenter;


    @BindView(R.id.tv_message)
    TextView tvMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_message)
    void onClickTvMessage(){
        mPresenter.showDtailProducrion(1);
    }

    @Override
    public void showDetailProduct(long id) {
        startActivity(DetailProductActivity.getIntent(this));
    }
}
