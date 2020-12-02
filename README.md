## ChatView
A ChatAdapter Library for Android

## Demo
<p align="left">
  <img width=255, height=515, src="https://github.com/lau1944/chatview/blob/main/Screenshot_1606633654.png" />
</p>


## Ready

Add dependency
``` groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
        ...
    }
}
```

```groovy
  dependencies {
      implementation 'com.github.lau1944:chatview:1.0.1'
  }
```

## Usage


### Sample Chat List
``` kotlin
for (i: Int in 0..5) {
     list.add(
         ChatMessage(false, "hello", "jimmy",
            "icon", "2020-12-31")
          )
     list.add(ChatMessage(true, "hi", "me",
            "icon", "2020-12-31"))
        }

```


### Submit list & assign adapter
``` kotlin
  adapter = ChatAdapter()
  adapter.submitList(list)
  recyclerView.adapter = adapter
```


### Add New Message to current List
``` kotlin
adapter.addMessageToList(ChatMessage(false, "new message", "jimmy",
            "icon", "2020-12-31"))
```


### Add Observer To Adapter
 
 Whenever a new message is added, observer callback would be notified
```kotlin
  adapter.addObserver(object: NewChatObserver{
            override fun update(newMessage: ChatMessage) {
                Log.d("DemoActivity", newMessage.message.toString())
            }

        })
```


This package is mainly for my own project, it only supports text only view

If you want more features, like include photos, videos,etc... Welcome to put on issues, I would work on it 😀

