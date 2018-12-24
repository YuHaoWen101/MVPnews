package com.example.ge63vr.model

class Data {
    /**
     * date : 20181030
     * stories : [{"images":["https://pic1.zhimg.com/v2-20674ad315d32850223648bc17aaeb30.jpg"],"type":0,"id":9700003,"ga_prefix":"103018","title":"降噪耳机是如何实现降噪的？"},{"images":["https://pic2.zhimg.com/v2-e804cc5d45855a12984197634feda825.jpg"],"type":0,"id":9699926,"ga_prefix":"103016","title":"- 睡午觉对身体好不好？\r\n- 开心就好"},{"images":["https://pic1.zhimg.com/v2-267aa986549f2fffcb6f760372d3d954.jpg"],"type":0,"id":9700138,"ga_prefix":"103015","title":"坐车顶遭限高杆撞头致死，后车录视频的人该负责吗？"},{"images":["https://pic3.zhimg.com/v2-f356f05b01f0194c6dfd5357409df496.jpg"],"type":0,"id":9700127,"ga_prefix":"103013","title":"「10 万年薪难觅驻地科研人才」，真的没有少打一个 0"},{"images":["https://pic1.zhimg.com/v2-005642e01195c92762261bc4f39a53e0.jpg"],"type":0,"id":9700101,"ga_prefix":"103012","title":"大误 · 名侦探夏洛克"},{"images":["https://pic3.zhimg.com/v2-2c1aa9f7becc509bab85831d19f316ba.jpg"],"type":0,"id":9700047,"ga_prefix":"103010","title":"「嘀嘀嘀嘀嘀嘀」成为了声音商标，但它并不是第一个"},{"images":["https://pic1.zhimg.com/v2-641b4d3742d0b2fecfc3ee2d3bac2244.jpg"],"type":0,"id":9699970,"ga_prefix":"103009","title":"2020 年发射一个人造月亮代替路灯？我有点迷"},{"images":["https://pic1.zhimg.com/v2-07f64b28c1ca7217f498df36f6b7534c.jpg"],"type":0,"id":9699868,"ga_prefix":"103008","title":"北冰洋汽水和果子面包的前世今生"},{"images":["https://pic3.zhimg.com/v2-4567f770f7f8b5281f48ad8a79f2e38a.jpg"],"type":0,"id":9699738,"ga_prefix":"103007","title":"在未来，互联网会把中关村和 798 变成蒲公英状的胶囊"},{"images":["https://pic4.zhimg.com/v2-7b9b3f6933808456022143bb072f4953.jpg"],"type":0,"id":9700018,"ga_prefix":"103007","title":"李开复提问：未来 25 年，你理想的 AI 时代长什么样？"},{"images":["https://pic3.zhimg.com/v2-e21fa94ecf6caec734f437a797068c72.jpg"],"type":0,"id":9700089,"ga_prefix":"103006","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic2.zhimg.com/v2-75125136c564237a5e5a2f4def0811e5.jpg","type":0,"id":9700138,"ga_prefix":"103015","title":"坐车顶遭限高杆撞头致死，后车录视频的人该负责吗？"},{"image":"https://pic4.zhimg.com/v2-37fdf8e4800077b42c99de3d297b7683.jpg","type":0,"id":9700127,"ga_prefix":"103013","title":"「10 万年薪难觅驻地科研人才」，真的没有少打一个 0"},{"image":"https://pic4.zhimg.com/v2-4b0a18b6c0757d26452ef70679cdbb0b.jpg","type":0,"id":9700018,"ga_prefix":"103007","title":"李开复提问：未来 25 年，你理想的 AI 时代长什么样？"},{"image":"https://pic3.zhimg.com/v2-6832bbb196b28baa8921e66c0159aed6.jpg","type":0,"id":9699970,"ga_prefix":"103009","title":"2020 年发射一个人造月亮代替路灯？我有点迷"},{"image":"https://pic2.zhimg.com/v2-b31a23acb80a5ec7c45731e166dc97cd.jpg","type":0,"id":9700047,"ga_prefix":"103010","title":"「嘀嘀嘀嘀嘀嘀」成为了声音商标，但它并不是第一个"}]
     */

    var date: String? = null
    var stories: List<StoriesBean>? = null
    var top_stories: List<TopStoriesBean>? = null

    class StoriesBean {
        /**
         * images : ["https://pic1.zhimg.com/v2-20674ad315d32850223648bc17aaeb30.jpg"]
         * type : 0
         * id : 9700003
         * ga_prefix : 103018
         * title : 降噪耳机是如何实现降噪的？
         */
        var date: String? = null
        var type: Int = 0
        var id: Int = 0
        var ga_prefix: String? = null
        var title: String? = null
        var images: List<String>? = null
    }

    class TopStoriesBean {
        /**
         * image : https://pic2.zhimg.com/v2-75125136c564237a5e5a2f4def0811e5.jpg
         * type : 0
         * id : 9700138
         * ga_prefix : 103015
         * title : 坐车顶遭限高杆撞头致死，后车录视频的人该负责吗？
         */

        var image: String? = null
        var type: Int = 0
        var id: Int = 0
        var ga_prefix: String? = null
        var title: String? = null
    }
}
