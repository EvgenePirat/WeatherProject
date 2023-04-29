package com.example.weatherproject.service.helper.impl;

import com.example.weatherproject.entity.weather.WeatherInfo;
import com.example.weatherproject.service.helper.OpenWeather;
import com.google.gson.Gson;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class OpenWeatherImpl implements OpenWeather {

    private final String API_KEY = "fd822bdb702f59fc5d97101f58ac9934";

    private final String URL_SERVICE = "https://api.openweathermap.org/data/2.5/weather";

    private final Gson json = new Gson();

    public WeatherInfo searchWeatherWithCity(String city) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet(URL_SERVICE + "?q=" + city + "&lang=ua"+"&appid=" + API_KEY+"&units=metric");
            CloseableHttpResponse response = httpClient.execute(request);
            try {
                String responseBody = EntityUtils.toString(response.getEntity());
                WeatherInfo weatherInfo = json.fromJson(responseBody, WeatherInfo.class);
                return weatherInfo;
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            httpClient.close();
        }
    }
}
