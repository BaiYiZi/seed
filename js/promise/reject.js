const myPromise = new Promise((resolve, reject) => {
  reject("reject")
});

(() => {
  myPromise.then(value => {
    console.log(value)
  }).then(value => {
    console.log(value)
  }).catch(err => {
    console.log(err)
  })
})();
