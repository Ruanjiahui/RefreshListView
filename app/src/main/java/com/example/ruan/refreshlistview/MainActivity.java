package com.example.ruan.refreshlistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.ruan.refreshlistview.View.FreshLinearLayout;
import com.example.ruan.refreshlistview.View.RefreshSideListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 自定义listview   下拉，侧滑刷新
 * <p/>
 * 实现的基本原理是，自定义listview里面实现下拉滑动和右侧滑动的操作，   外面的linearlayou也是实现下拉滑动的操作
 * 为什么要实现两个下拉滑动呢，
 * 因为如果没有监听linearyout , 当你listview没有填充满的时候，也可以说当listview没有item项的时候呢，你想下拉刷新是没有任何操作会实现的,
 * 因为只监听listview的滑动只能是滑动listview   , 当时listview没有item就是说listview理论上是存在的，当时实际上它是隐藏掉的，所以滑动时没有
 * 任何效果的，
 * 这个时候你应该监听listview表面的linearyout布局的滑动事件，当item没有填充满屏幕的时候，你滑动空白的地方就是实际滑动的就是linearlayout
 * 所以监听多一层就是为了保险没有item情况的下拉
 * <p/>
 * 那为什么只做linearyout的下拉监听事件就行了，为什么还要做listview的滑动下拉监听事件呢，因为当没有item的时候就是滑动的是linearyout，当时有
 * item的时候其实是因为那个滑动的优先级问题，listview实在linearyout上面嘛，所以滑动的优先级肯定是listview优先，这个时候只监听linearyout其实也是
 * 没有任何效果的，还有一种解决办法应该是判断当listview出现滑动时就设置linearyout的优先级为第一位，这样子可能会出现想要的效果，当时这种办法我
 * 没有操作过，所以不知道会不会出现预期的效果
 */
public class MainActivity extends Activity implements ItemClick, ItemClick.RreshInterface {


    @Bind(R.id.slideListView)
    RefreshSideListView slideListView;
    @Bind(R.id.myLinear)
    FreshLinearLayout myLinear;

    private SideListViewAdapter adapter = null;
    private ArrayList<Object> list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        list = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            list.add(getItem("this is listview item" + i));


        adapter = new SideListViewAdapter(this, list);
        slideListView.setAdapter(adapter);

//        这个方法是实现设置下拉最大的高度
//        slideListView.setMaxHeight();
//        myLinear.setMaxHeight();


        //刷新的接口
        slideListView.setRreshClick(this);
        myLinear.setRreshClick(this);
    }

    private Object getItem(String title) {
        Item item = new Item();
        item.setTitle(title);
        item.setSubtitle("大家好");
        item.setLogo(getResources().getDrawable(R.mipmap.ic_launcher));
        return item;
    }

    /**
     * item的子控件点击事件
     *
     * @param position 子控件在列表中属于第几个
     * @param View     子控件的序号靠最左边为第0个
     */
    @Override
    public void OnClick(int position, int View) {

    }

    /**
     * 下拉刷新界面的接口
     */
    @Override
    public void RreshData() {

    }
}
