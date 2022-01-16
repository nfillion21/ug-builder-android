/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pgm.poolp.ugbuilder.model

import androidx.compose.runtime.Immutable

@Immutable
data class Team(
    val name: String,
    val courses: Int,
    val imageUrl: String
)

/*
val teams = listOf(
    Team("Alliance du vieux monde", 58, "http://ug-data.xyz/ugbuilder/troll_slayer.jpeg"),
    Team("Amazones", 121, "https://images.unsplash.com/photo-1422246358533-95dcd3d48961"),
    Team("Bas-fonds", 78, "https://images.unsplash.com/photo-1507679799987-c73779587ccf"),
    Team("Elfes noirs", 118, "https://images.unsplash.com/photo-1551218808-94e220e084d2"),
    Team("Elfes sylvains", 423, "https://images.unsplash.com/photo-1493932484895-752d1471eab5"),
    Team("Élus du chaos", 92, "https://images.unsplash.com/photo-1517840545241-b491010a8af4"),
    Team("Gobelins", 165, "https://images.unsplash.com/photo-1518676590629-3dcbd9c5a5c9"),
    Team("Halflings", 164, "https://images.unsplash.com/photo-1528870884180-5649b20f6435"),
    Team("Hauts elfes", 326, "https://images.unsplash.com/photo-1526312426976-f4d754fa9bd6"),
    Team("Hommes-lézards", 305, "https://images.unsplash.com/photo-1471560090527-d1af5e4e6eb6"),
    Team("Horreurs nécromantiques", 212, "https://images.unsplash.com/photo-1454922915609-78549ad709bb"),
    Team("Humains", 172, "https://images.unsplash.com/photo-1461344577544-4e5dc9487184"),
    Team("Morts-vivants", 321, "https://images.unsplash.com/photo-1542567455-cd733f23fbb1"),
    Team("Nains", 118, "https://images.unsplash.com/photo-1535223289827-42f1e9919769"),
    Team("Nains du chaos", 118, "https://images.unsplash.com/photo-1535223289827-42f1e9919769"),
    Team("Noblesse impériale", 118, "https://images.unsplash.com/photo-1535223289827-42f1e9919769"),
    Team("Nordiques", 118, "https://images.unsplash.com/photo-1535223289827-42f1e9919769"),
    Team("Nurgle", 118, "https://images.unsplash.com/photo-1535223289827-42f1e9919769"),
    Team("Ogres", 118, "https://images.unsplash.com/photo-1535223289827-42f1e9919769"),
    Team("Orques", 118, "https://images.unsplash.com/photo-1535223289827-42f1e9919769"),
    Team("Orques noirs", 118, "https://images.unsplash.com/photo-1535223289827-42f1e9919769"),
    Team("Renégats du chaos", 118, "https://images.unsplash.com/photo-1535223289827-42f1e9919769"),
    Team("Rois des tombes", 118, "https://images.unsplash.com/photo-1535223289827-42f1e9919769"),
    Team("Skavens", 118, "https://images.unsplash.com/photo-1535223289827-42f1e9919769"),
    Team("Snotlings", 118, "https://images.unsplash.com/photo-1535223289827-42f1e9919769"),
    Team("Union elfique", 118, "https://images.unsplash.com/photo-1535223289827-42f1e9919769"),
    Team("Vampires", 118, "https://images.unsplash.com/photo-1535223289827-42f1e9919769"),
)
*/

val teams = listOf(
    Team("Teenage Mutant Ninja Turtles", 58, "https://images.unsplash.com/photo-1422246358533-95dcd3d48961"),
    Team("Spiderman", 121, "https://images.unsplash.com/photo-1422246358533-95dcd3d48961")
)
