<template>
  <div class="home">

    <div v-loading="loading" element-loading-text="加载中..." style="min-height: 35vw;">
      <div class="banner">
        <div class="bg" ref="bg">
          <transition name="fade">
            <div>
            <div v-for="(item, i) in banner" v-if="i===mark"  :key="i" style="position:absolute" @mouseover="stopTimer"
              @mouseout="startTimer">
              <img v-if="item.picUrl" class="img1" :src="item.picUrl" />
              <img v-if="item.picUrl2" class="img2 a" :src="item.picUrl2" />
              <!-- <img v-if="item.picUrl3" class="img3 b" :src="item.picUrl3" /> -->
            </div>
          </div>
          </transition>
        </div>
        <div class="page">
          <ul class="dots">
            <li class="dot-active" v-for="(item, i) in banner" :class="{ 'dot': i != mark }" :key="i"  @click="change(i)"></li>
          </ul>
        </div>
      </div>
      <div class="activity-panel">
        <ul class="box">
          <li class="content" v-for="(iitem, j) in panelContents" :key="j" >
            <img class="i" :src="iitem.picUrl">
            <a class="cover-link"></a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>
<script>
//import {mapState} from 'vuex'
export default {
  name: "mall",
  // computed: {
  // 	...mapState(['introduction'])
  // },
  data() {
    return {
      timer: '',
      loading: false,
      mark: 0,
      banner:
        [
          {
            "id": 70,
            "panelId": 7,
            "type": 0,
            "productId": 150635087972564,
            "sortOrder": 1,
            "fullUrl": "",
            "picUrl": "https://s2.loli.net/2023/01/19/rY95AmqMKj2F6LX.jpg",
            "picUrl2": "",
            "picUrl3": "",
            "created": 1569839898000,
            "updated": 1569843454000,
            "salePrice": 1,
            "productName": "支付测试商品 IPhone X 全面屏 全面绽放",
            "subTitle": "此仅为支付测试商品 拍下不会发货",
            "productImageBig": "https://ooo.0o0.ooo/2019/09/30/CAZ6QrXPBoh5aIT.png"
          },
          {
            "id": 33,
            "panelId": 7,
            "type": 0,
            "productId": 150642571432835,
            "sortOrder": 2,
            "fullUrl": "",
            "picUrl": "https://s2.loli.net/2023/01/19/mdYHlxCe49oNPg6.jpg",
            "picUrl2": "",
            "picUrl3": "",
            "created": 1523970502000,
            "updated": 1524192439000,
            "salePrice": 1,
            "productName": "捐赠商品",
            "subTitle": "您的捐赠将用于本站维护 给您带来更好的体验",
            "productImageBig": "https://ooo.0o0.ooo/2018/11/04/5bdeba4028e90.png"
          },
          {
            "id": 34,
            "panelId": 7,
            "type": 0,
            "productId": 150635087972564,
            "sortOrder": 3,
            "fullUrl": null,
            "picUrl": "https://s2.loli.net/2023/01/19/Xjk8i4xdnCZhsYR.jpg",
            "picUrl2": "",
            "picUrl3": "",
            "created": 1523970510000,
            "updated": 1523970512000,
            "salePrice": 1,
            "productName": "支付测试商品 IPhone X 全面屏 全面绽放",
            "subTitle": "此仅为支付测试商品 拍下不会发货",
            "productImageBig": "https://s1.ax1x.com/2018/05/19/Ccdiid.png"
          }
        ],
      panelContents:
        [
          {
            "id": 25,
            "panelId": 8,
            "type": 0,
            "productId": 150642571432835,
            "sortOrder": 1,
            "fullUrl": "https://www.smartisan.com/jianguo3-accessories",
            "picUrl": "https://s2.loli.net/2023/01/19/SKsdQxc213lNpRt.jpg",
            "picUrl2": null,
            "picUrl3": null,
            "created": 1523790463000,
            "updated": 1524151234000,
            "salePrice": 1,
            "productName": "捐赠商品",
            "subTitle": "您的捐赠将用于本站维护 给您带来更好的体验",
            "productImageBig": "https://resource.smartisan.com/resource/6/610400xinpinpeijian.jpg"
          },
          {
            "id": 26,
            "panelId": 8,
            "type": 0,
            "productId": 150635087972564,
            "sortOrder": 2,
            "fullUrl": "https://www.smartisan.com/service/#/tradein",
            "picUrl": "https://s2.loli.net/2023/01/19/OvB6PQqkWSNXlfA.jpg",
            "picUrl2": null,
            "picUrl3": null,
            "created": 1523790480000,
            "updated": 1524151248000,
            "salePrice": 1,
            "productName": "支付测试商品 IPhone X 全面屏 全面绽放",
            "subTitle": "此仅为支付测试商品 拍下不会发货",
            "productImageBig": "https://resource.smartisan.com/resource/6/610400yijiuhuanxin.jpg"
          },
          {
            "id": 27,
            "panelId": 8,
            "type": 0,
            "productId": 150642571432835,
            "sortOrder": 3,
            "fullUrl": "https://www.smartisan.com/category?id=69",
            "picUrl": "https://s2.loli.net/2023/01/19/GibcUzQor6nyJ5Z.jpg",
            "picUrl2": null,
            "picUrl3": null,
            "created": 1523790504000,
            "updated": 1524151261000,
            "salePrice": 1,
            "productName": "捐赠商品",
            "subTitle": "您的捐赠将用于本站维护 给您带来更好的体验",
            "productImageBig": "https://resource.smartisan.com/resource/4/489673079577637073.png"
          },
          {
            "id": 28,
            "panelId": 8,
            "type": 0,
            "productId": 150635087972564,
            "sortOrder": 4,
            "fullUrl": "https://www.smartisan.com/",
            "picUrl": "https://s2.loli.net/2023/01/19/VFRxXlHf6nUrp1G.jpg",
            "picUrl2": null,
            "picUrl3": null,
            "created": 1523790538000,
            "updated": 1524151273000,
            "salePrice": 1,
            "productName": "支付测试商品 IPhone X 全面屏 全面绽放",
            "subTitle": "此仅为支付测试商品 拍下不会发货",
            "productImageBig": "https://resource.smartisan.com/resource/fe6ab43348a43152b4001b4454d206ac.jpg"
          }
        ]
    }
  },
  watch: {

  },
  methods: {
    autoPlay() {
      this.mark++
      if (this.mark > this.banner.length - 1) {
        // 当遍历到最后一张图片置零
        this.mark = 0
      }
    },
    play() {
      // 每2.5s自动切换
      this.timer = setInterval(this.autoPlay, 2500)
    },
    change(i) {
      this.mark = i
    },
    startTimer() {
      this.timer = setInterval(this.autoPlay, 2500)
    },
    stopTimer() {
      clearInterval(this.timer)
    },
    startTimer() {
      this.timer = setInterval(this.autoPlay, 2500)
    },
    stopTimer() {
      clearInterval(this.timer)
    },
    change (i) {
        this.mark = i
      },
  },
  mounted() {

  }
}
</script>
<style rel="stylesheet/scss" scoped>
.home {
  display: flex;
  flex-direction: column;
  margin: auto;
}

.no-info {
  padding: 100px 0;
  text-align: center;
  font-size: 30px;
  display: flex;
  flex-direction: column;

}

.no-info .no-data {
  align-self: center;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity .5s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}

.page {
  position: absolute;
  width: 100%;
  top: 470px;
  z-index: 30;

}

.page .dots {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;

}

.page .dots .dot-active {
  display: inline-block;
  width: 15px;
  height: 15px;
  background-color: whitesmoke;
  border-radius: 8px;
  margin-right: 10px;
  cursor: pointer;
}

.page .dots .dot {
  opacity: 0.2;
}

.activity-panel {
  width: 1220px;
  margin: 0 auto;

}

.activity-panel .box {
  overflow: hidden;
  position: relative;
  z-index: 0;
  margin-top: 25px;
  box-sizing: border-box;
  border: 1px solid rgba(0, 0, 0, .14);
  border-radius: 8px;
  background: #fff;
  box-shadow: 0 3px 8px -6px rgba(0, 0, 0, .1);
}

.activity-panel .content {
  float: left;
  position: relative;
  box-sizing: border-box;
  width: 25%;
  height: 200px;
  text-align: center;
}

.activity-panel .content ::before {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 1;
  box-sizing: border-box;
  border-left: 1px solid #f2f2f2;
  border-left: 1px solid rgba(0, 0, 0, .1);
  width: 100%;
  height: 100%;
  content: "";
  pointer-events: none;
}

.activity-panel .i {
  width: 305px;
  height: 200px;
}

.activity-panel .cover-link {
  cursor: pointer;
  display: block;
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: 4;
  background: url(data:image/gif;base64,R0lGODlhAQABAIAAAP///////yH5BAEHAAEALAAAAAABAAEAAAICTAEAOw==) repeat;
}

.activity-panel a {
  color: #5079d9;
  cursor: pointer;
  transition: all .15s ease-out;
  text-decoration: none;
}

.activity-panel a:hover {
  box-shadow: inset 0 0 38px rgba(0, 0, 0, .08);
  transition: all .15s ease;
}

.banner,
.banner span,
.banner div {
  font-family: "Microsoft YaHei";
  transition: all .3s;
  transition-timing-function: linear;
}

.banner {
  cursor: pointer;
  perspective: 3000px;
  position: relative;
  z-index: 19;
  margin: 0 auto;
  width: 1220px;
}

.bg {
  position: relative;
  width: 1220px;
  height: 500px;
  margin: 20px auto;
  background-size: 100% 100%;
  border-radius: 10px;
  transform-style: preserve-3d;
  transform-origin: 50% 50%;
  transform: rotateY(0deg) rotateX(0deg);

}

.bg div {
  position: relative;
  height: 100%;
  width: 100%;
}

.img1 {
  display: block;
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  border-radius: 10px;
}

.img2 {
  display: block;
  position: absolute;
  width: 100%;
  height: 100%;
  bottom: 5px;
  left: 0;
  background-size: 95% 100%;
  border-radius: 10px;
}

.img3 {
  display: block;
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  border-radius: 10px;
}

.a {
  z-index: 20;
  transform: translateZ(40px);
}

.b {
  z-index: 20;
  transform: translateZ(30px);
}

.c {
  transform: translateZ(0px);
}

.sk_item {
  width: 170px;
  height: 225px;
  padding: 0 14px 0 15px;

}

.sk_item div {
  width: 100%;
}

.sk_item a {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  transition: all .3s;

}

.sk_item a:hover {
  transform: translateY(-5px);
}

.sk_item img {
  width: 130px;
  height: 130px;
  margin: 17px 0;
}

.sk_item .sk_item_name {
  color: #999;
  display: block;
  max-width: 100%;
  _width: 100%;
  overflow: hidden;
  font-size: 12px;
  text-align: left;
  height: 32px;
  line-height: 16px;
  word-wrap: break-word;
  word-break: break-all;
}

.sk_item .sk_item_price {
  padding: 3px 0;
  height: 25px;
}

.sk_item .price_new {
  font-size: 18px;
  font-weight: 700;
  margin-right: 8px;
  color: #f10214;
}

.price_origin {
  color: #999;
  font-size: 12px;
}

.box {
  overflow: hidden;
  position: relative;
  z-index: 0;
  margin-top: 29px;
  box-sizing: border-box;
  border: 1px solid rgba(0, 0, 0, .14);
  border-radius: 8px;
  background: #fff;
  box-shadow: 0 3px 8px -6px rgba(0, 0, 0, .1);

}

ul.box {
  display: flex;

}

ul.box li {
  flex: 1;

}

ul.box li img {
  display: block;
  width: 305px;
  height: 200px;
}

.mt30 {
  margin-top: 30px;
}

.hot {
  display: flex;

}

.hot>div {
  flex: 1;
  width: 25%;
}

.floors {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  align-items: center;

}

.floors .imgbanner {
  width: 50%;
  height: 430px;

}

.floors .imgbanner .cover-link {
  cursor: pointer;
  display: block;
  position: absolute;
  top: 60px;
  left: 0;
  width: 50%;
  height: 430px;
  z-index: 4;
  background: url(data:image/gif;base64,R0lGODlhAQABAIAAAP///////yH5BAEHAAEALAAAAAABAAEAAAICTAEAOw==) repeat;
}

.floors .imgbanner .cover-link:hover {
  box-shadow: inset 0 0 38px rgba(0, 0, 0, .08);
  transition: all .15s ease;
}

.floors .imgbanner img {
  display: block;
  width: 100%;
  height: 100%;
}
</style>

