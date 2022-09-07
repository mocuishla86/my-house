package learning.mockito;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class MyAppTest {

    @Test
    void shouldShowMessageIfItIsHot() {
        WeatherService weatherServiceMock = mock(WeatherService.class);
        BannerService bannerServiceMock = mock(BannerService.class);
        MyApp sut = new MyApp(weatherServiceMock, bannerServiceMock);
        when(weatherServiceMock.getTemperature("Madrid")).thenReturn(32);

        sut.checkWeather();

        verify(bannerServiceMock).writeMessage("It's hot");
    }

    @Test
    void shouldDontShowMessageIfItIsNotHot() {
        WeatherService weatherServiceMock = mock(WeatherService.class);
        BannerService bannerServiceMock = mock(BannerService.class);
        MyApp sut = new MyApp(weatherServiceMock, bannerServiceMock);
        when(weatherServiceMock.getTemperature("Madrid")).thenReturn(15);

        sut.checkWeather();

        verify(bannerServiceMock, never()).writeMessage("It's hot");
    }
}