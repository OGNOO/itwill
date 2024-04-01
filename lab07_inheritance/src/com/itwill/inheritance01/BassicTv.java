package com.itwill.inheritance01;

// extends Object는 생략 가능
public class BassicTv {
	// 상수 정의:
	public static final int MIN_CHANNEL = 0;
	public static final int MAX_CHANNEL = 2;
	public static final int MIN_VOLUME = 0;
	public static final int MAX_VOLUME = 2;

	// 필드
	private boolean powerOn; // 전원 상태 (true -> ON, false -> OFF)
	private int channel; // 현재 채널 번호
	private int volume; // 음량

	// constructor

	public BassicTv() {
	}

	public BassicTv(boolean powerOn, int channel, int volume) {
		this.powerOn = powerOn;
		this.channel = channel;
		this.volume = volume;
	}

	// method

	public boolean isPowerOn() {
		return powerOn;
	}

	public void setPowerOn(boolean powerOn) {
		this.powerOn = powerOn;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	/**
	 * TV가 켜져 있으면 끄고, TV가 꺼져 있으면 켬 powerOn의 값이
	 */
	public boolean powerOnOff() {
		powerOn = powerOn ? false : true;
		return powerOn;
	}

	// 메서드
	/**
	 * TV가 켜져 있으면 끄고, TV가 꺼져 있는 켬. powerOn의 값이 true 이면 false 변경, powerOn의 값이 false
	 * 이면 true 로 변경.
	 * 
	 * @return powerOn의 값.
	 */
	public int channelUp() {
		if (powerOn) {
			if (channel == MAX_CHANNEL) {
				channel = MIN_CHANNEL;
			} else {
				channel++;
			}
		}
		return channel;
	}

	/**
	 * channelUp. 현재 채널의 값을 1 감소. 만약 현재 채널 값이 채널의 최솟값(MIN_CHANNEL)인 경우, 채널의
	 * 최댓값(MIN_CHANNEL)으로 변경
	 * 
	 * @return 바뀐 현재 채널 값
	 */
	public int channelDown() {
		if (powerOn) {
			if (channel == MIN_CHANNEL) {
				channel = MAX_CHANNEL;
			} else {
				channel--;
			}
		}
		return channel;
	}

	/**
	 * volumeUp. 현재 음량의 값을 1 증가. 만약 현재 음량 값이 최댓값(MAX_CHANNEL)인 경우, 음량을 변경하지 않음
	 * 
	 * @return 바뀐 현재 음량 값
	 */
	public int volumeUp() {
		if (powerOn) {
			if (volume == MAX_VOLUME) {
				volume = MAX_VOLUME;
			} else {
				volume++;
			}
		}
		return volume;
	}

	/**
	 * volumeUp. 현재 음량의 값을 1 감소. 만약 현재 음량 값이 최솟값(MIN_CHANNEL)인 경우, 음량을 변경하지 않음
	 * 
	 * @return 바뀐 현재 음량 값
	 */
	public int volumeDown() {
		if (powerOn) {
			if (volume == MIN_VOLUME) {
				volume = MIN_VOLUME;
			} else {
				volume--;
			}
		}
		return volume;
	}
}
