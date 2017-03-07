# DroidTrackingAOP
An AOP way to implement tracking feature inside Android apps.

The idea about this `tracker` library came to my mind after implementing
tracking page state via [Omniture](https://marketing.adobe.com/resources/help/en_US/mobile/android/states.html).

For the previous version, inside class `Application`, there is code logic related to
initializing `Adobe` components which should be kept off the application logic.

After code refactoring, you only have to add `@Trackable` to [any tracking methods](https://github.com/rayworks/DroidTrackingAOP/blob/master/app/src/main/java/com/rayworks/droidtrackingaop/tracking/TrackingDelegator.java#L36) and specify
the tracking scope (via `TargetScope`), the tracking requests will be sent out.

Currently, the tracking client is `Omniture`.
``` java
class OmnitureTracker extends DroidTrackerImpl
```
Because ```DroidTrackerImpl``` is a abstract class, you can also have other 3rd part library ( e.g Mixpanel ) inplaced.


Credit
--------
Inspired from [Android-AOP-Sample](https://github.com/android10/Android-AOPExample)



License
-----------------

     Copyright 2017 rayworks

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.