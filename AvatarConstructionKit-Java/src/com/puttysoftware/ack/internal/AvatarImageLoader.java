/*  Fantastle: A World-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

Any questions should be directed to the author via email at: fantastle@worldwizard.net
 */
package com.puttysoftware.ack.internal;

import java.io.IOException;

import com.puttysoftware.ack.AvatarImageModel;
import com.puttysoftware.ack.ColorReplaceRules;
import com.puttysoftware.images.BufferedImageIcon;

public class AvatarImageLoader {
    public static BufferedImageIcon load(final int familyID,
            final ColorReplaceRules rules) throws IOException {
        final String imageExt = ".png";
        final String name = "/assets/avatar/"
                + Integer.toHexString(familyID).toUpperCase() + imageExt;
        return rules.applyAll(ImageLoader.load(name,
                AvatarImageLoader.class.getResource(name)));
    }

    public static BufferedImageIcon loadFromModel(final AvatarImageModel model)
            throws IOException {
        final String imageExt = ".png";
        final String name = "/assets/avatar/"
                + Integer.toHexString(model.getAvatarFamilyID()).toUpperCase()
                + imageExt;
        return model.getRules().applyAll(ImageLoader.load(name,
                AvatarImageLoader.class.getResource(name)));
    }

    public static BufferedImageIcon loadWeapon(final int weaponID,
            final ColorReplaceRules rules) throws IOException {
        final String imageExt = ".png";
        final String name = "/assets/weapon/"
                + Integer.toHexString(weaponID).toUpperCase() + imageExt;
        return rules.applyAll(ImageLoader.load(name,
                AvatarImageLoader.class.getResource(name)));
    }

    public static BufferedImageIcon loadAccessory(final int accessoryID,
            final ColorReplaceRules rules) throws IOException {
        final String imageExt = ".png";
        final String name = "/assets/accessory/"
                + Integer.toHexString(accessoryID).toUpperCase() + imageExt;
        return rules.applyAll(ImageLoader.load(name,
                AvatarImageLoader.class.getResource(name)));
    }
}
