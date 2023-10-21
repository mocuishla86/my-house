package learning.mockito;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class MockitoTest {
    @Test
    public void black() {
        WeatherService weatherServiceMock = mock(WeatherService.class);

        when(weatherServiceMock.getTemperature("Madrid")).thenReturn(24);
        when(weatherServiceMock.getTemperature("Sevilla")).thenReturn(35);

        System.out.println(weatherServiceMock.getTemperature("Madrid"));
        System.out.println(weatherServiceMock.getTemperature("Sevilla"));
        System.out.println(weatherServiceMock.getTemperature("Palencia"));

    }

    @Test
    public void white() {
        BannerService bannerServiceMock = mock(BannerService.class);

        bannerServiceMock.writeMessage("hola");

        verify(bannerServiceMock).writeMessage("hola");

    }

    @Test
    public void green() {
        BannerService bannerServiceMock = mock(BannerService.class);

        bannerServiceMock.writeMessage("hola");

        verify(bannerServiceMock).writeMessage("hola");

    }
}
