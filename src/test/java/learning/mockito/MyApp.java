package learning.mockito;

public class MyApp {

    WeatherService weatherService;
    BannerService bannerService;

    public MyApp(WeatherService weatherService, BannerService bannerService) {
        this.weatherService = weatherService;
        this.bannerService = bannerService;
    }

    void checkWeather() {
        if (weatherService.getTemperature("Madrid") > 15) {
            bannerService.writeMessage("It's hot");
        }
    }
}
