package com.gabilheri.githubviewer.fragments;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.base.DefaultFragment;

import java.util.Random;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 1/6/15.
 */
public class Fragment404 extends DefaultFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_404, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ImageView im = (ImageView) view.findViewById(R.id.octocat);

        TypedArray images = getResources().obtainTypedArray(R.array.octocats);

        Random rand = new Random();
        im.setImageResource(images.getResourceId(rand.nextInt(6), R.drawable.octocat1));
    }
}
