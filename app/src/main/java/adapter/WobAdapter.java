package adapter;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.empowerit.R;

import bean.WobsResults;

public class WobAdapter extends PagerAdapter {

	private WobsResults resultsToShow;
	private LayoutInflater mLayoutInflater;
	private Context context;
	/*
	 * private FrameLayout webviewLayout; private HorizontalScrollView
	 * horizontalScrollView1;
	 */
	private View rootView;
	private TextView wobName;
	private TextView wobDescription;
	private TextView wobLocation;
	private ImageView wobProfile;

	public WobAdapter(Context context, WobsResults wobsResult) {
		this.resultsToShow = wobsResult;
		this.context = context;
		this.mLayoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		int qtWobs = resultsToShow.listWobsResult.size();
		return qtWobs;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;

	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		rootView = mLayoutInflater.inflate(R.layout.wob_adapter, container,
				false);
		wobName = (TextView) rootView.findViewById(R.id.tv_name);
		wobDescription = (TextView) rootView.findViewById(R.id.tv_descrip);
		wobLocation = (TextView) rootView.findViewById(R.id.tv_loc);
		wobProfile = (ImageView) rootView.findViewById(R.id.image_wob);
		loadInfo(position);
		container.addView(rootView);

		return rootView;
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	private void loadInfo(int position) {

		wobName.setText(resultsToShow.listWobsResult.get(position).getName());
		wobDescription.setText(resultsToShow.listWobsResult.get(position)
				.getDescription());
		wobLocation.setText(resultsToShow.listWobsResult.get(position)
				.getLocalization());
		
		DownloadImagemAsyncTask downloadtask = new DownloadImagemAsyncTask();
		downloadtask.execute(resultsToShow.listWobsResult.get(position).getUrlImage());

	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	class DownloadImagemAsyncTask extends AsyncTask<String, Void, Bitmap> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			String urlString = params[0];
			try {
				URL url = new URL(urlString);
				HttpURLConnection conexao = (HttpURLConnection) url
						.openConnection();
				conexao.setRequestMethod("GET");
				conexao.setDoInput(true);
				conexao.connect();
				InputStream is = conexao.getInputStream();
				Bitmap imagem = BitmapFactory.decodeStream(is);
				return imagem;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			if (result != null) {
				ImageView img = (ImageView) rootView
						.findViewById(R.id.image_wob);
				img.setImageBitmap(result);
			} else {
			}
		}
	}

}
