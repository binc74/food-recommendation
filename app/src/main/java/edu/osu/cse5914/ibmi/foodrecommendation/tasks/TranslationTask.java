package edu.osu.cse5914.ibmi.foodrecommendation.tasks;

import android.os.AsyncTask;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.language_translator.v3.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.developer_cloud.language_translator.v3.model.TranslationResult;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

import edu.osu.cse5914.ibmi.foodrecommendation.data.ProjectApi;

public class TranslationTask extends AsyncTask<String, Void, String> {
    protected TextView mText;
    protected LanguageTranslator mTranslator;

    public TranslationTask(TextView tv) {
        mText = tv;

        IamOptions options = new IamOptions.Builder()
                .apiKey(ProjectApi.TRANSLATION_API)
                .build();

        mTranslator = new LanguageTranslator("2018-05-01", options);
        mTranslator.setEndPoint("https://gateway.watsonplatform.net/language-translator/api");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... objects) {
        TranslateOptions translateOptions = new TranslateOptions.Builder()
                .addText(objects[0])
                .modelId(objects[1])
                .build();

        TranslationResult result = mTranslator.translate(translateOptions)
                .execute();

        return result.getTranslations().get(0).getTranslationOutput();
    }

    @Override
    protected void onProgressUpdate(Void[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String o) {
        super.onPostExecute(o);
    //ZH
        mText.setText(o);
    }
}
