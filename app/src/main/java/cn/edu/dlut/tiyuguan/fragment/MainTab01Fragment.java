package cn.edu.dlut.tiyuguan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;
import java.util.LinkedHashMap;

import cn.edu.dlut.tiyuguan.ChildAnimationExample;
import cn.edu.dlut.tiyuguan.R;
import cn.edu.dlut.tiyuguan.activity.FitAdviceActivity;
import cn.edu.dlut.tiyuguan.activity.MapActivity;
import cn.edu.dlut.tiyuguan.activity.OrderRemindActivity;
import cn.edu.dlut.tiyuguan.activity.SportAnalysisActivity;
import cn.edu.dlut.tiyuguan.activity.TiyuguanGuideActivity;
import cn.edu.dlut.tiyuguan.activity.WeatherActivity;
import cn.edu.dlut.tiyuguan.activity.WebActivity;
import cn.edu.dlut.tiyuguan.adapterview.MyGridView;
import cn.edu.dlut.tiyuguan.model.News;
import cn.edu.dlut.tiyuguan.model.Sport;
import cn.edu.dlut.tiyuguan.util.AppUtil;
import cn.edu.dlut.tiyuguan.util.ToastUtil;

public class MainTab01Fragment extends Fragment implements BaseSliderView.OnSliderClickListener {
    private SliderLayout mDemoSlider;
    private View view;


    private int resImage[] = {R.drawable.ic_action_cloudy, R.drawable.ic_action_calculator, R.drawable.ic_action_location,
            R.drawable.ic_action_help, R.drawable.ic_action_alarm, R.drawable.ic_action_book};
    private String resString[] = {"大连天气", "我的运动分析", "附近的人", "体育馆指南", "预约提醒", "运动指南"};

    protected LinkedHashMap<String, String> url_maps;

    private LayoutInflater inflater;
    private ViewGroup container;

    Bundle savedInstanceState;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        initView();
        return this.view;
    }

    private void initView() {
        this.view = (getLayoutInflater(savedInstanceState)).inflate(R.layout.main_tab_01, null, false);
        initSliderView();
        MyGridView gridView = (MyGridView) view.findViewById(R.id.homegridview);
        if (gridView != null) {
            gridView.setAdapter(new MyGridViewAdapter());
            gridView.setSelector(R.color.blue);
        }
    }

    /**
     * init the news image view
     **/
    private void initSliderView() {
        //从本地加载
        HashMap<String, Integer> file_maps = new HashMap<>();
        file_maps.put("Hannibal", R.drawable.hannibal);
        file_maps.put("Big Bang Theory", R.drawable.bigbang);
        file_maps.put("House of Cards", R.drawable.house);
        file_maps.put("Game of Thrones", R.drawable.game_of_thrones);

        //从网络加载
        url_maps = new LinkedHashMap<>();
        HashMap<String, News> newsMap = Sport.getInstance().getNewsMap();
        if (newsMap != null) {
            for (String key : newsMap.keySet()) {
                url_maps.put(key, newsMap.get(key).getFirstImageSrc());
            }
        } else {
            url_maps.put("学校第十届教职工代表大会第四次会议开幕", "http://www.dlut.edu.cn/_mediafile/testdlut2/2015/03/05/_thumb/2rntyetvov.jpg");
            url_maps.put("高水平艺术团开考 郭东明校长视察现场", "http://upload.dlut.edu.cn/2015/0310/1425956025882.jpg");
            url_maps.put("2014.11.28辽宁省各市知识产权局局长参观", "http://chuangxin.dlut.edu.cn/_mediafile/chuangxin/2014/11/28/_thumb/2p5u5s1dh8.jpg");
            url_maps.put("3月4日大连市委书记唐军视察我院学生开发的3D打印机", "http://chuangxin.dlut.edu.cn/_mediafile/chuangxin/2015/03/09/4ppw8qfo2b.jpg");
        }

        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            //add your extra information
            textSliderView.bundle(new Bundle());
            Bundle bundle = textSliderView.getBundle();
            if (bundle != null)
                bundle.putString("extra", name);
            else
                System.out.println("bundle is null");
            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new ChildAnimationExample());
        mDemoSlider.setDuration(4000);
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (view != null)
            ((ScrollView) view).smoothScrollTo(0, 0);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        // TODO Auto-generated method stub
        News model = Sport.getInstance().getNewsMap().get(slider.getBundle().get("extra"));
        if(model == null) {
            ToastUtil.showInfoToast(getActivity(), slider.getBundle().get("extra") + "");
            return;
        } else {
            String url = model.getUrl();
            String title = model.getTitle();
            Intent showNews = new Intent(this.getActivity(), WebActivity.class);
            AppUtil.debugV("===TAG===", "url is " + url);
            showNews.putExtra(WebActivity.URL_EXTRAL, url);
            showNews.putExtra(WebActivity.URL_TITLE,title);
            startActivity(showNews);
        }
        return;

    }

    //GridView的适配器
    class MyGridViewAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 6;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View view = getActivity().getLayoutInflater().inflate(R.layout.grid_item_view, null);
            ((ImageView) view.findViewById(R.id.grid_item_view_imageview)).setImageResource(resImage[position]);
            ((TextView) view.findViewById(R.id.grid_item_view_textview)).setText(resString[position]);
            view.setTag(resString[position]);//给view加便签
            view.setOnClickListener(new GridViewOnClickListener());//添加监听事件
            return view;
        }

    }

    class GridViewOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            String tag = v.getTag().toString();
            switch (tag) {
                case "大连天气":
                    startActivity(new Intent(getActivity(), WeatherActivity.class));
                    break;
                case "体育馆指南":
                    startActivity(new Intent(getActivity(), TiyuguanGuideActivity.class));
                    break;
                case "运动指南":
                    startActivity(new Intent(getActivity(), FitAdviceActivity.class));
                    break;
                case "我的运动分析":
                    startActivity(new Intent(getActivity(), SportAnalysisActivity.class));
                    break;
                case "附近的人":
                    startActivity(new Intent(getActivity(), MapActivity.class));
                    break;
                case "预约提醒":
                    startActivity(new Intent(getActivity(), OrderRemindActivity.class));
                    break;
            }
        }
    }
}




