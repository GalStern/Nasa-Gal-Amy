package com.example.nasa;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

enum DownloadStatus { IDLE, PROCESSING, NOT_INITIALISED, FAILED_OR_EMPTY, OK }

class GetRawData extends AsyncTask<String, Void, String> {
    private static final String TAG = "GetRawData";

    private DownloadStatus mDownloadStatus;

    public GetRawData() {
        this.mDownloadStatus = DownloadStatus.IDLE;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, "onPostExecute: parameter = " + s);
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        if(strings == null) {
            mDownloadStatus = DownloadStatus.NOT_INITIALISED;
            return null;
        }

        try {

            mDownloadStatus = DownloadStatus.PROCESSING;
            URL url = new URL(strings[0]);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int response = connection.getResponseCode();
            Log.d(TAG, "doInBackground: The response code was " + response);

            StringBuilder result = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            //Looping through all the input in the reader
            //Draw attention to assignment that happens before the condition is tested
            //line gets results of the readLine() and tests if it is null than it reads the line, and appends the end of the line with \
            for(String line = reader.readLine(); line != null; line = reader.readLine()) {
                result.append(line).append("\n");
            }
            //Return the result (http connection) in String format
            mDownloadStatus = DownloadStatus.OK;
            return result.toString();

            //Check to see whether any exceptions are thrown
        } catch(MalformedURLException e) {
            Log.e(TAG, "doInBackground: Invalid URL " + e.getMessage() );
        } catch(IOException e) {
            Log.e(TAG, "doInBackground: IO Exception reading data: " + e.getMessage() );
            //If you forget to connect to internet
        } catch(SecurityException e) {
            Log.e(TAG, "doInBackground: Security Exception. Needs permission? " + e.getMessage());
            //finally block must be executed regardless of thrown exceptions
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
            if(reader != null) {
                try {
                    reader.close();
                } catch(IOException e) {
                    Log.e(TAG, "doInBackground: Error closing stream " + e.getMessage() );
                }
            }
        }
        //Returns status if it has failed/empty
        mDownloadStatus = DownloadStatus.FAILED_OR_EMPTY;
        return null;
    }
}