package com.example.eagle.lalala.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;


import com.example.eagle.lalala.bean.User;
import com.example.eagle.lalala.utils.MyViewHolder;
import com.example.eagle.lalala.utils.PingYinUtil;
import com.example.eagle.lalala.utils.PinyinComparator;

import com.example.eagle.lalala.R;
import java.util.Collections;
import java.util.List;

public class ContactAdapter extends BaseAdapter implements SectionIndexer {
	private Context mContext;
	private List<User> UserInfos;// 好友信息

	public ContactAdapter(Context mContext, List<User> UserInfos) {
		this.mContext = mContext;
		this.UserInfos = UserInfos;
		// 排序(实现了中英文混排)
		Collections.sort(UserInfos, new PinyinComparator());
	}

	@Override
	public int getCount() {
		return UserInfos.size();
	}

	@Override
	public Object getItem(int position) {
		return UserInfos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		User user = UserInfos.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.contact_item, null);

		}
		ImageView ivAvatar = MyViewHolder.get(convertView,
				R.id.contactitem_avatar_iv);
		TextView tvCatalog = MyViewHolder.get(convertView,
				R.id.contactitem_catalog);
		TextView tvNick = MyViewHolder.get(convertView, R.id.contactitem_nick);
		String catalog = PingYinUtil.converterToFirstSpell(user.getName())
				.substring(0, 1);
		if (position == 0) {
			tvCatalog.setVisibility(View.VISIBLE);
			tvCatalog.setText(catalog);
		} else {
			User Nextuser = UserInfos.get(position - 1);
			String lastCatalog = PingYinUtil.converterToFirstSpell(
					Nextuser.getName()).substring(0, 1);
			if (catalog.equals(lastCatalog)) {
				tvCatalog.setVisibility(View.GONE);
			} else {
				tvCatalog.setVisibility(View.VISIBLE);
				tvCatalog.setText(catalog);
			}
		}

		ivAvatar.setImageResource(R.drawable.head);
		tvNick.setText(user.getName());
		return convertView;
	}

	@Override
	public int getPositionForSection(int section) {
		for (int i = 0; i < UserInfos.size(); i++) {
			User user = UserInfos.get(i);
			String l = PingYinUtil.converterToFirstSpell(user.getName())
					.substring(0, 1);
			char firstChar = l.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		return 0;
	}

	@Override
	public int getSectionForPosition(int position) {
		return 0;
	}

	@Override
	public Object[] getSections() {
		return null;
	}
}