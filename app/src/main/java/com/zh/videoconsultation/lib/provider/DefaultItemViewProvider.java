package com.zh.videoconsultation.lib.provider;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zh.videoconsultation.R;
import com.zh.videoconsultation.lib.IViewProvider;

public class DefaultItemViewProvider implements IViewProvider<String> {
    @Override
    public int resLayout() {
        return R.layout.scroll_picker_default_item_layout;
    }

    @Override
    public void onBindView(@NonNull View view, @Nullable String text) {
        TextView tv = view.findViewById(R.id.tv_content);
        tv.setText(text);
        view.setTag(text);
        tv.setTextSize(18);
    }

    @Override
    public void updateView(@NonNull View itemView, boolean isSelected) {
        TextView tv = itemView.findViewById(R.id.tv_content);
        tv.setTextSize(isSelected ? 18 : 14);
        tv.setTextColor(Color.parseColor(isSelected ? "#ED5275" : "#000000"));
    }
}
