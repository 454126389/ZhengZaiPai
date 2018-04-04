package app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook.BookAdd;

import java.util.List;

import app.conqueror.com.zhengzaipai.util.SpUtil;

/**
 * Created by Administrator on 2017/7/20.
 */

public class BookAddPresenter extends BookAddContract.Presenter {
    @Override
    public void onStart() {
        mView.initDialog();

    }

    @Override
    public void phb(String id,  List<String> bookList) {
        mView.showDialog();

        String content=bookList.toString().substring(1, bookList.toString().length() - 1).replaceAll(" ", "");
        content=content.replaceAll("-", ",");
        mRxManage.add(mModel.phb(id,content).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
                        SpUtil.changeWatchUserListBookList(SpUtil.getChoise(),bookList);
                        mView.addSuc();
                        mView.suc();
//                        mView.showMsg("设置成功");
                    } else {
                        mView.fail();
//                        mView.showMsg("失败!");

                    }
                }, e -> {
                        mView.fail();
//                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }
}