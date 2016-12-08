package com.lin.mu.model;

import java.util.List;

/**
 * Created by lin on 2016/12/1.
 */

public class Music {

    /**
     * result : {"songCount":931,"songs":[{"id":36894385,"name":"周杰伦","artists":[{"id":1040035,"name":"李雪莱","picUrl":null}],"album":{"id":3080398,"name":"李雪莱的Demo","artist":{"id":0,"name":"","picUrl":null},"picUrl":"http://p1.music.126.net/CvoyIDli1TB3OSp6d3PChQ==/2539871862437570.jpg"},"audio":"http://m2.music.126.net/DVTJWTJoNUSDjLMsKXtxaw==/3393092905675046.mp3","djProgramId":0,"page":"http://music.163.com/m/song/36894385"},{"id":427609796,"name":"周杰伦《彩虹》","artists":[{"id":1202004,"name":"姜创钢琴","picUrl":null}],"album":{"id":34857346,"name":"周杰伦《彩虹》","artist":{"id":0,"name":"","picUrl":null},"picUrl":"http://p1.music.126.net/g3gsJCmKMZ5Ul5lSAsPIvg==/18177126231131590.jpg"},"audio":"http://m2.music.126.net/16ZuBMUPnMf7FueBTVxXgA==/18799449811872323.mp3","djProgramId":0,"page":"http://music.163.com/m/song/427609796"}]}
     * code : 200
     */

    private ResultBean result;
    private int code;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class ResultBean {
        /**
         * songCount : 931
         * songs : [{"id":36894385,"name":"周杰伦","artists":[{"id":1040035,"name":"李雪莱","picUrl":null}],"album":{"id":3080398,"name":"李雪莱的Demo","artist":{"id":0,"name":"","picUrl":null},"picUrl":"http://p1.music.126.net/CvoyIDli1TB3OSp6d3PChQ==/2539871862437570.jpg"},"audio":"http://m2.music.126.net/DVTJWTJoNUSDjLMsKXtxaw==/3393092905675046.mp3","djProgramId":0,"page":"http://music.163.com/m/song/36894385"},{"id":427609796,"name":"周杰伦《彩虹》","artists":[{"id":1202004,"name":"姜创钢琴","picUrl":null}],"album":{"id":34857346,"name":"周杰伦《彩虹》","artist":{"id":0,"name":"","picUrl":null},"picUrl":"http://p1.music.126.net/g3gsJCmKMZ5Ul5lSAsPIvg==/18177126231131590.jpg"},"audio":"http://m2.music.126.net/16ZuBMUPnMf7FueBTVxXgA==/18799449811872323.mp3","djProgramId":0,"page":"http://music.163.com/m/song/427609796"}]
         */

        private int songCount;
        private List<SongsBean> songs;

        public int getSongCount() {
            return songCount;
        }

        public void setSongCount(int songCount) {
            this.songCount = songCount;
        }

        public List<SongsBean> getSongs() {
            return songs;
        }

        public void setSongs(List<SongsBean> songs) {
            this.songs = songs;
        }

        public static class SongsBean {
            /**
             * id : 36894385
             * name : 周杰伦
             * artists : [{"id":1040035,"name":"李雪莱","picUrl":null}]
             * album : {"id":3080398,"name":"李雪莱的Demo","artist":{"id":0,"name":"","picUrl":null},"picUrl":"http://p1.music.126.net/CvoyIDli1TB3OSp6d3PChQ==/2539871862437570.jpg"}
             * audio : http://m2.music.126.net/DVTJWTJoNUSDjLMsKXtxaw==/3393092905675046.mp3
             * djProgramId : 0
             * page : http://music.163.com/m/song/36894385
             */

            private int id;
            private String name;
            private AlbumBean album;
            private String audio;
            private int djProgramId;
            private String page;
            private String picUrl;
            private List<ArtistsBean> artists;
            public boolean isPlaying = false;// 是否在播放录音

            public boolean isPlaying() {
                return isPlaying;
            }

            public void setPlaying(boolean playing) {
                isPlaying = playing;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public AlbumBean getAlbum() {
                return album;
            }

            public void setAlbum(AlbumBean album) {
                this.album = album;
            }

            public String getAudio() {
                return audio;
            }

            public void setAudio(String audio) {
                this.audio = audio;
            }

            public int getDjProgramId() {
                return djProgramId;
            }

            public void setDjProgramId(int djProgramId) {
                this.djProgramId = djProgramId;
            }

            public String getPage() {
                return page;
            }

            public void setPage(String page) {
                this.page = page;
            }

            public List<ArtistsBean> getArtists() {
                return artists;
            }

            public void setArtists(List<ArtistsBean> artists) {
                this.artists = artists;
            }

            public static class AlbumBean {
                /**
                 * id : 3080398
                 * name : 李雪莱的Demo
                 * artist : {"id":0,"name":"","picUrl":null}
                 * picUrl : http://p1.music.126.net/CvoyIDli1TB3OSp6d3PChQ==/2539871862437570.jpg
                 */

                private int id;
                private String name;
                private ArtistBean artist;
                private String picUrl;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public ArtistBean getArtist() {
                    return artist;
                }

                public void setArtist(ArtistBean artist) {
                    this.artist = artist;
                }

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public static class ArtistBean {
                    /**
                     * id : 0
                     * name : 
                     * picUrl : null
                     */

                    private int id;
                    private String name;
                    private Object picUrl;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public Object getPicUrl() {
                        return picUrl;
                    }

                    public void setPicUrl(Object picUrl) {
                        this.picUrl = picUrl;
                    }
                }
            }

            public static class ArtistsBean {
                /**
                 * id : 1040035
                 * name : 李雪莱
                 * picUrl : null
                 */

                private int id;
                private String name;
                private Object picUrl;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public Object getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(Object picUrl) {
                    this.picUrl = picUrl;
                }
            }
        }
    }
}
